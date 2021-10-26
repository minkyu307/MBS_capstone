package mbs_capsotme.mbs.controller;

import mbs_capsotme.mbs.domain.Department;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Status;
import mbs_capsotme.mbs.service.DepartmentService;
import mbs_capsotme.mbs.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final DepartmentService departmentService;

    public MemberController(MemberService memberService, DepartmentService departmentService) {
        this.memberService = memberService;
        this.departmentService = departmentService;
    }


    @GetMapping(value = "/members/join")
    public String joinPage() {
        return "members/join";
    }

    @PostMapping(value = "/members/new")
    public String join(MemberForm form) {
        Member member = new Member();
        member.setAddress(form.getAddress());
        member.setEmail(form.getEmail());
        member.setLogin_id(form.getLogin_id());
        member.setMemberName(form.getMemberName());
        member.setPassword(form.getPassword());
        member.setPhoneNumber(form.getPhoneNumber());
        member.setPosition(form.getPosition());
        member.setLoginStatus(Status.OUT);
        member.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Department department = departmentService.findOne(form.getDepartmentName()).get();
        department.setNumberOfMember(department.getNumberOfMember() + 1);
        departmentService.save(department);
        member.setDepartment(department);
        memberService.joinAndSave(member);
        return "redirect:/";
    }

    //로그인시 id와 password 일치검사 후 상태를 IN으로 변경하고 세션에 저장
    @RequestMapping(value = "/success")
    public String logIn(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String loginId = request.getParameter("id");
        Member member = memberService.findByLoginId(loginId).get();

        member.setLoginStatus(Status.IN);
        session.setAttribute("member",member);
        member.setSessionId(session.getId());
        memberService.joinAndSave(member);

        return "division";
    }

    //로그아웃시 상태를 out으로 변경하고 세션 종료
    @RequestMapping(value = "/members/logout")
    public String logOut(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");
        Member newMember = memberService.findOne(member.getId()).get();
        newMember.setLoginStatus(Status.OUT);
        newMember.setSessionId(null);
        memberService.joinAndSave(newMember);
        session.invalidate();

        return "redirect:/";
    }
}
