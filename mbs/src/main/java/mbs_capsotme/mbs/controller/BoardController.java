package mbs_capsotme.mbs.controller;

import lombok.extern.log4j.Log4j2;
import mbs_capsotme.mbs.domain.Board;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@Log4j2
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = "/board/boardList")
    public String showBoardList(Model model, HttpServletRequest req) {

        List<Board> boards = boardService.findAllBoards();
        model.addAttribute("boards", boards);

        int div = Integer.parseInt(req.getParameter("division"));
        if (div == 1)
            return "divFunction/boardListDiv";
        else
            return "function/boardList";
    }

    @RequestMapping(value = "/board/newBoard")
    public String createNewBoard(HttpServletRequest req) {

        int div = Integer.parseInt(req.getParameter("division"));
        if (div == 1)
            return "divFunction/newBoardDiv";
        else
            return "function/newBoard";
    }

    @RequestMapping(value = "/board/saveNewBoard")
    public String saveNewBoard(HttpServletRequest req, BoardForm boardForm) {

        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");

        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContents(boardForm.getContents());
        board.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        board.setLastModifiedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        board.setMember(member);
        board.setViews(0);
        boardService.save(board);

        int div = Integer.parseInt(req.getParameter("division"));
        if (div == 1)
            return "redirect:/board/boardList?division=1";
        else
            return "redirect:/board/boardList?division=0";
    }

    @RequestMapping(value = "/board/boardRead")
    public String readBoard(HttpServletRequest req, Model model) {

        Long id = Long.parseLong(req.getParameter("id"));
        Board board = boardService.findOne(id).get();
        board.setViews(board.getViews() + 1);
        boardService.save(board);

        model.addAttribute("board", board);

        int div = Integer.parseInt(req.getParameter("division"));
        if (div == 1)
            return "divFunction/boardReadDiv";
        else
            return "function/boardRead";
    }

    @RequestMapping(value = "/board/update")
    public String updateBoard(HttpServletRequest req, Model model) {

        Long id = Long.parseLong(req.getParameter("id"));
        Board board = boardService.findOne(id).get();
        model.addAttribute("board", board);

        int div = Integer.parseInt(req.getParameter("division"));
        if (div == 1)
            return "divFunction/boardUpdateDiv";
        else
            return "function/boardUpdate";
    }

    @RequestMapping(value = "/board/saveBoardUpdate")
    public String saveUpdate(HttpServletRequest req, Model model, BoardForm boardForm) {

        Board board = boardService.findOne(boardForm.getId()).get();
        board.setTitle(boardForm.getTitle());
        board.setContents(boardForm.getContents());
        board.setLastModifiedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        boardService.save(board);

        int div = Integer.parseInt(req.getParameter("division"));
        if (div == 1)
            return "redirect:/board/boardList?division=1";
        else
            return "redirect:/board/boardList?division=0";
    }

    @RequestMapping(value = "/board/delete")
    public String deleteBoard(HttpServletRequest req) {

        Long id = Long.parseLong(req.getParameter("id"));
        boardService.deleteBoard(id);

        int div = Integer.parseInt(req.getParameter("division"));
        if (div == 1)
            return "redirect:/board/boardList?division=1";
        else
            return "redirect:/board/boardList?division=0";
    }

    @RequestMapping(value = "/board/searchBoard")
    public String searchBoard(HttpServletRequest req, Model model, HttpServletResponse response) throws IOException {

        int select = Integer.parseInt(req.getParameter("select"));
        int div = Integer.parseInt(req.getParameter("division"));
        String search = req.getParameter("search");

        List<Board> boards = boardService.searchBoard(select, search);
        if (!boards.isEmpty()) {
            model.addAttribute("boards", boards);

            if (div == 1)
                return "divFunction/boardListDiv";
            else
                return "function/boardList";
        }

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('찾으시는 글이 없습니다.'); location.href='/board/boardList';</script>");
        out.flush();
        if (div == 1)
            return "redirect:/board/boardList?division=1";
        else
            return "redirect:/board/boardList?division=0";
    }

    @RequestMapping(value = "/board/fileUpload")
    public String fileUpload(HttpServletRequest req, @RequestParam("files")List<MultipartFile> files) throws Exception{

        String basePath = "C:\\Users\\김민규\\Desktop\\MBS_capstone\\mbs\\src\\main\\resources\\files";

        for(MultipartFile file : files) {
            String originalName = file.getOriginalFilename();
            String filePath = basePath + "/" + originalName;
            File dest = new File(filePath);
            file.transferTo(dest);
        }
        return "redirect:/board/boardRead?division=0&id="+req.getParameter("id");
    }
}
