package mbs_capsotme.mbs.controller;

import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.service.MemberService;
import mbs_capsotme.mbs.service.MemoService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

        int div =  Integer.parseInt(req.getParameter("division"));
        if (div==1)
            return "divFunction/memoListDiv";
        else
            return "function/memoList";
    }

    @RequestMapping(value = "/memo/delete")
    public String deleteMemo(HttpServletRequest req, Model model) {

        Long id = Long.parseLong(req.getParameter("id"));
        memoService.deleteMemo(id);

        int div =  Integer.parseInt(req.getParameter("division"));
        if (div==1)
            return "redirect:/memo/memoList?division=1";
        else
            return "redirect:/memo/memoList?division=0";
    }

    @GetMapping(value = "/memo/adminDelete")
    public String adminDeleteMemo(HttpServletRequest req){
        Long id = Long.parseLong(req.getParameter("id"));
        memoService.deleteMemo(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/memo/newMemo")
    public String createNewMemo(HttpServletRequest req) {

        int div =  Integer.parseInt(req.getParameter("division"));
        if (div==1)
            return "divFunction/createNewMemoDiv";
        else
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

        int div =  Integer.parseInt(req.getParameter("division"));
        if (div==1)
            return "redirect:/memo/memoList?division=1";
        else
            return "redirect:/memo/memoList?division=0";
    }

    @RequestMapping(value = "/memo/readMemo")
    public String readMemo(HttpServletRequest req, Model model) {

        Long id = Long.parseLong(req.getParameter("id"));
        Memo memo = memoService.findOne(id).get();

        model.addAttribute("memo", memo);

        int div =  Integer.parseInt(req.getParameter("division"));
        if (div==1)
            return "divFunction/readMemoDiv";
        else
            return "function/readMemo";
    }

    @RequestMapping(value = "/memo/updateMemo")
    public String updateMemo(HttpServletRequest req, Model model) {

        Long id= Long.parseLong(req.getParameter("id"));
        String contents = req.getParameter("contents");

        Memo memo = memoService.findOne(id).get();
        memo.setContents(contents);
        memoService.clearPersist();

        int div =  Integer.parseInt(req.getParameter("division"));
        if (div==1)
            return "redirect:/memo/memoList?division=1";
        else
            return "redirect:/memo/memoList?division=0";
    }


}
