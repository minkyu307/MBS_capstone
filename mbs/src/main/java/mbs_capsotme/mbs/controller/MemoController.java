package mbs_capsotme.mbs.controller;

import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.service.MemberService;
import mbs_capsotme.mbs.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String showMemoList(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();
        Member member = (Member)session.getAttribute("member");
        List<Memo> memos = memoService.findAllByMember(member);

        for (Memo memo : memos) {
            System.out.println("memo.getContents() = " + memo.getContents());
        }

        model.addAttribute("memos",memos);

        return "function/memoList";
    }

    @RequestMapping(value = "/memo/delete")
    public String deleteMemo(HttpServletRequest req, Model model){
        Long id = Long.parseLong(req.getParameter("id"));
        memoService.deleteMemo(id);
        HttpSession session = req.getSession();
        Member member = (Member)session.getAttribute("member");
        List<Memo> memos = memoService.findAllByMember(member);

        for (Memo memo : memos) {
            System.out.println("memo.getContents() = " + memo.getContents());
        }

        model.addAttribute("memos",memos);

        return "function/memoList";
    }
}
