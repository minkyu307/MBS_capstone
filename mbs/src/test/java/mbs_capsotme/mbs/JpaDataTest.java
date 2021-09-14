package mbs_capsotme.mbs;

import mbs_capsotme.mbs.domain.Board;
import mbs_capsotme.mbs.domain.Department;
import mbs_capsotme.mbs.domain.Member;
import mbs_capsotme.mbs.domain.Memo;
import mbs_capsotme.mbs.repository.MemberRepository;
import mbs_capsotme.mbs.service.BoardService;
import mbs_capsotme.mbs.service.DepartmentService;
import mbs_capsotme.mbs.service.MemberService;
import mbs_capsotme.mbs.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    void joinTest() {
        for (int i = 0; i < 5; i++) {
            Member member = new Member();
            member.setMemberName("A"+i);
            memberService.join(member);
        }

        for (int i = 0; i < 5; i++) {
            Board board = new Board();
            board.setContents("A" + i);
            boardService.join(board);
        }

        for (int i = 0; i < 5; i++) {
            Memo memo = new Memo();
            memo.setContents("A" + i);
            memoService.join(memo);
        }

        for (int i = 0; i < 5; i++) {
            Department department = new Department();
            department.setNumberOfMember(i+1);
            departmentService.join(department);
        }
    }

    @Test
    void searchTest() {
        List<Member> members = memberService.findMembers();
        for (Member member : members) {
            System.out.println("member.getId() = " + member.getId());
            System.out.println("member.getMemberName() = " + member.getMemberName());
        }
        Optional<Member> findOneMember = memberService.findOne(2L);
        System.out.println("findOneMember = " + findOneMember.get().getMemberName());

        //memo board dept search coding
    }

}
