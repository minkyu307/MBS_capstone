package mbs_capsotme.mbs.service;

import lombok.extern.log4j.Log4j2;
import mbs_capsotme.mbs.domain.Board;
import mbs_capsotme.mbs.domain.UploadFiles;
import mbs_capsotme.mbs.repository.BoardRepository;
import mbs_capsotme.mbs.repository.UploadFileRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Component
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;
    private final UploadFileRepository uploadFileRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UploadFileRepository fileRepository) {
        this.boardRepository = boardRepository;
        this.uploadFileRepository = fileRepository;
    }

    public Long save(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> findOne(Long boardId) {
        return boardRepository.findById(boardId);
    }

    public Board findBoardWithWriterId(Long id){
        return boardRepository.findByWriterId(id);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteBoard(id);
    }

    public List<Board> searchBoard(int select, String search) {

        if (select == 1) {
            return boardRepository.searchBoardByTitleOrContents(search);
        }
        else if (select == 2) {
            return boardRepository.searchBoardByTitle(search);
        }
        else{
            return boardRepository.searchBoardByContents(search);
        }
    }

    public void clearPersist() {
        boardRepository.clearPersist();
    }

    public void fileUploadWithBoard(List<MultipartFile> files, Board board, String basePath) throws IOException {

        log.info("uploadStart");
        for(MultipartFile file : files) {
            String originalName = file.getOriginalFilename();
            String extension = file.getContentType();
            UploadFiles uploadFiles = new UploadFiles();
            String uuid = UUID.randomUUID().toString();
            uploadFiles.setUuid(uuid);
            uploadFiles.setFileName(originalName);
            uploadFiles.setExtension(extension);
            uploadFiles.setBoard(board);
            uploadFileRepository.save(uploadFiles);
            String filePath = basePath + "/" + uuid + "__" +originalName;
            File dest = new File(filePath);
            file.transferTo(dest);
        }
        log.info("uploadEnd");

    }

    public void fileDeleteWithBoard(String uuid,File file){
        if(file.exists()){
            file.delete();
            uploadFileRepository.deleteFile(uuid);
        }
    }

    public void deleteBoardByWriterId(Long id){
        boardRepository.deleteBoardByWriterId(id);
    }

    public void fileDeleteWithBoardId(Long id){
        uploadFileRepository.deleteFileByBoardId(id);
    }
}
