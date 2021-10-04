package mbs_capsotme.mbs.service;

import mbs_capsotme.mbs.domain.Board;
import mbs_capsotme.mbs.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
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

    public void clearPersist() {
        boardRepository.clearPersist();
    }
}
