package mbs_capsotme.mbs;

import mbs_capsotme.mbs.domain.*;
import mbs_capsotme.mbs.repository.MemberRepository;
import mbs_capsotme.mbs.service.BoardService;
import mbs_capsotme.mbs.service.DepartmentService;
import mbs_capsotme.mbs.service.MemberService;
import mbs_capsotme.mbs.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JpaDataTest {

    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;
    @Autowired
    MemoService memoService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EntityManager em;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Test
    void 부서만들기(){
        Department d= new Department();

        d.setDepartmentName("비서과");
        d.setNumberOfMember(0);
        departmentService.save(d);

    }

    @Test
    void 저장테스트() {
        /*for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setMemberName("A"+i);
            memberService.joinAndSave(member);
        }

        for (int i = 0; i < 5; i++) {
            Board board = new Board();
            board.setContents("A" + i);
            boardService.save(board);
        }

        for (int i = 0; i < 5; i++) {
            Memo memo = new Memo();
            memo.setContents("A" + i);
            memoService.save(memo);
        }

        for (int i = 0; i < 5; i++) {
            Department department = new Department();
            department.setNumberOfMember(i+1);
            departmentService.save(department);
        }*/


        Member member = new Member();
        member.setLogin_id("2");
        member.setPassword(passwordEncoder.encode("2"));
        member.setMemberName("kim2");
        member.setLoginStatus(Status.OUT);
        member.setRole(Role.USER);
        memberService.joinAndSave(member);
    }


    @Test
    void 조회테스트() {

        //멤버 조회
        List<Member> members = memberService.findMembers();
        for (Member member : members) {
            System.out.println("member.getId() = " + member.getId());
            System.out.println("member.getMemberName() = " + member.getMemberName());
        }
        Optional<Member> findOneMember = memberService.findOne(2L);
        System.out.println("findOneMember = " + findOneMember.get().getMemberName());

        //메모 조회
        List<Memo> memos = memoService.findMemos();
        for (Memo memo : memos) {
            System.out.println("memo.getId() = " + memo.getId());
            System.out.println("memo.getContents() = " + memo.getContents());
        }
        Optional<Memo> findOndMemo = memoService.findOne(11L);
        System.out.println("findOndMemo = " + findOndMemo.get().getContents());

        //게시판 조회
        List<Board> boards = boardService.findAllBoards();
        for (Board board : boards) {
            System.out.println("board.getId() = " + board.getId());
            System.out.println("board = " + board.getContents());
        }
        Optional<Board> findOneBoard = boardService.findOne(6L);
        System.out.println("findOneBoard = " + findOneBoard.get().getContents());

        //부서 조회
        List<Department> departments = departmentService.findDepartments();
        for (Department department : departments) {
            System.out.println("department.getId() = " + department.getId());
            System.out.println("department.getNumberOfMember() = " + department.getNumberOfMember());
        }
        Optional<Department> findOneDepartment = departmentService.findOne(16L);
        System.out.println("findOneDepartment.get().getNumberOfMember() = " + findOneDepartment.get().getNumberOfMember());
    }

    @Test
    void 조인테스트() {
        Member member = new Member();
        member.setMemberName("kim");
        memberService.joinAndSave(member);

        Board board = new Board();
        board.setContents("Hello");
        board.setMember(member);
        boardService.save(board);

        memberService.clearPersist();
        boardService.clearPersist();

        List<Board> boards = em.createQuery("select b from Board b join b.member m",Board.class).getResultList();
        for (Board board1 : boards) {
            System.out.println("board1 = " + board1.getId());
            System.out.println("board1 = " + board1.getContents());
            System.out.println("board1 = " + board1.getMember().getId());
        }
    }

}
