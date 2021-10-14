package mbs_capsotme.mbs.controller;

import lombok.extern.log4j.Log4j2;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Status;
import mbs_capsotme.mbs.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Log4j2
public class ChatController {

    private final MemberService memberService;

    public ChatController(MemberService memberService) {
        this.memberService = memberService;
    }


    @RequestMapping(value = "/chat/goChat")
    public String doChatWithMember(HttpServletRequest req, Model model) {

        List<Member> members = memberService.findMembers();
        int cnt=0;
        for (Member member : members) {
            if(member.getLoginStatus().equals(Status.IN))
                cnt++;
        }
        model.addAttribute("cnt", cnt);

        return "function/doChat";
    }

    @RequestMapping(value = "/chat")
    public void chatGET() {
        log.info("@ChatController, chatGET();");
    }
}
