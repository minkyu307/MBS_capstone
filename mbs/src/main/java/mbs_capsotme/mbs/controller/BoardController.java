package mbs_capsotme.mbs.controller;

import mbs_capsotme.mbs.domain.Board;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = "/board/boardList")
    public String showBoardList(Model model) {

        List<Board> boards = boardService.findAllBoards();
        model.addAttribute("boards", boards);

        return "function/boardList";
    }

    @RequestMapping(value = "/board/newBoard")
    public String createNewBoard() {
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

        return "redirect:/board/boardList";
    }

    @RequestMapping(value = "/board/boardRead")
    public String readBoard(HttpServletRequest req, Model model){

        Long id = Long.parseLong(req.getParameter("id"));
        Board board = boardService.findOne(id).get();

        model.addAttribute("board", board);

        return "/function/boardRead";
    }
}
