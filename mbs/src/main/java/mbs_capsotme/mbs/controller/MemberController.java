package mbs_capsotme.mbs.controller;

import lombok.RequiredArgsConstructor;
import mbs_capsotme.mbs.domain.*;
import mbs_capsotme.mbs.service.BoardService;
import mbs_capsotme.mbs.service.DepartmentService;
import mbs_capsotme.mbs.service.MemberService;
import mbs_capsotme.mbs.service.MemoService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final DepartmentService departmentService;
    private final MemoService memoService;
    private final BoardService boardService;
    private final BCryptPasswordEncoder passwordEncoder;


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
        member.setPassword(passwordEncoder.encode(form.getPassword()));
        member.setPhoneNumber(form.getPhoneNumber());
        member.setPosition(form.getPosition());
        member.setLoginStatus(Status.OUT);
        member.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        member.setRole(Role.USER);
        Department department = departmentService.findOne(form.getDepartmentName()).get();
        department.setNumberOfMember(department.getNumberOfMember() + 1);
        departmentService.save(department);
        member.setDepartment(department);
        memberService.joinAndSave(member);
        return "redirect:/";
    }

    @GetMapping(value = "/member/delete")
    public String deleteMember(HttpServletRequest req){

        Long id=Long.parseLong(req.getParameter("id"));
        Board board = boardService.findBoardWithWriterId(id);

        boardService.fileDeleteWithBoardId(board.getId());
        boardService.deleteBoardByWriterId(id);
        memoService.deleteMemoByWriterId(id);
        memberService.deleteMember(id);
        return "redirect:/admin";
    }

    //로그인시 id와 password 일치검사 후 상태를 IN으로 변경하고 세션에 저장
    @RequestMapping(value = "/success")
    public String logIn(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        String loginId = request.getParameter("id");
        Member member = memberService.findByLoginId(loginId).get();

        member.setLoginStatus(Status.IN);
        session.setAttribute("member",member);
        member.setSessionId(session.getId());
        memberService.joinAndSave(member);

        if (member.getRole().equals(Role.ADMIN)){
            return "redirect:/admin";
        }

        return "division";
    }

    @GetMapping(value = "/failure")
    public void loginFailure(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('아이디 또는 비밀번호가 잘못되었습니다.'); location.href='/home';</script>");
        out.flush();
    }

    @GetMapping(value = "/accessDenied")
    public void accessDenied(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('인가받지 않은 사용자입니다.'); location.href='/members/logout';</script>");
        out.flush();
    }

    //로그아웃시 상태를 out으로 변경하고 세션 종료
    /*@RequestMapping(value = "/members/logout")
    public String logOut(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Member member = (Member) session.getAttribute("member");
        Member newMember = memberService.findOne(member.getId()).get();
        newMember.setLoginStatus(Status.OUT);
        newMember.setSessionId(null);
        memberService.joinAndSave(newMember);
        session.invalidate();

        return "redirect:/";
    }*/

    //로그아웃시 상태를 out으로 변경하고 세션 종료
    @GetMapping(value = "/members/logout")
    public String logOut(HttpServletRequest req) {

        memberService.logOut(req);
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        List<Member> members = memberService.findMembers();
        List<Memo> memos = memoService.findMemos();
        List<Board> boards = boardService.findAllBoards();
        List<Department> departments = departmentService.findDepartments();
        model.addAttribute("members",members);
        model.addAttribute("memos",memos);
        model.addAttribute("boards",boards);
        model.addAttribute("departments",departments);
        return "admin";
    }
}
