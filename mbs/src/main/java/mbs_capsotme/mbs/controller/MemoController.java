package mbs_capsotme.mbs.controller;

import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.service.MemberService;
import mbs_capsotme.mbs.service.MemoService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MemoController {

    private final MemoService memoService;
    private final MemberService memberService;

    public MemoController(MemoService memoService, MemberService memberService) {
        this.memoService = memoService;
        this.memberService = memberService;
    }

    @RequestMapping(value = "/memo/memoList")
    public String showMemoList(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");
        List<Memo> memos = memoService.findAllByMember(member);
        model.addAttribute("memos", memos);
        return "function/memoList";
    }

    @RequestMapping(value = "/memo/delete")
    public String deleteMemo(HttpServletRequest req, Model model) {

        Long id = Long.parseLong(req.getParameter("id"));
        memoService.deleteMemo(id);
        return "redirect:/memo/memoList";
    }

    @RequestMapping(value = "/memo/newMemo")
    public String createNewMemo() {
        return "function/createNewMemo";
    }

    @RequestMapping(value = "/memo/saveNewMemo")
    public String saveNewMemo(HttpServletRequest req, Model model) {
        String contents = req.getParameter("contents");

        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");

        Memo memo = new Memo();
        memo.setContents(contents);
        memo.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        memo.setLastModifiedTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        memo.setMember(member);
        memoService.save(memo);

        return "redirect:/memo/memoList";
    }

    @RequestMapping(value = "/memo/readMemo")
    public String readMemo(HttpServletRequest req, Model model) {

        Long id = Long.parseLong(req.getParameter("id"));
        Memo memo = memoService.findOne(id).get();

        model.addAttribute("memo", memo);

        return "function/readMemo";
    }

    @RequestMapping(value = "/memo/updateMemo")
    public String updateMemo(HttpServletRequest req, Model model) {

        Long id= Long.parseLong(req.getParameter("id"));
        String contents = req.getParameter("contents");

        Memo memo = memoService.findOne(id).get();
        memo.setContents(contents);
        memoService.clearPersist();

        return "redirect:/memo/memoList";
    }


}
