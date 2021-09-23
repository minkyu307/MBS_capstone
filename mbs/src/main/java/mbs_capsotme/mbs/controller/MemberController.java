package mbs_capsotme.mbs.controller;

import mbs_capsotme.mbs.domain.Department;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.service.DepartmentService;
import mbs_capsotme.mbs.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String joinPage(){
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
        Department department = departmentService.findOne(form.getDepartmentName()).get();
        department.setNumberOfMember(department.getNumberOfMember()+1);
        departmentService.save(department);
        member.setDepartment(department);
        memberService.join(member);
        return "redirect:/";
    }

    @RequestMapping(value = "/members/login")
    public String logIn(MemberForm memberForm, Model model) {

        String loginId = memberForm.getLogin_id();
        String password = memberForm.getPassword();
        Member member = new Member();

        try {
            member = memberService.findByLoginId(loginId).get();
        }catch (Exception e){
            model.addAttribute("loginError","ID가 일치하지 않습니다.");
            return "home";
        }

        if(!password.equals(member.getPassword())){
            model.addAttribute("loginError","비밀번호가 일치하지 않습니다.");
            return "home";
        }

        memberForm.setMemberName(member.getMemberName());
        return "division";
    }
}
