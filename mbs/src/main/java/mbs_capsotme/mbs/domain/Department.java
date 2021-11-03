package mbs_capsotme.mbs.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;
    private int numberOfMember;

    private Long director_id;

    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Member> members = new ArrayList<>();

    public Member findDirectorByMemberId(List<Member> members){
        for (Member member : members) {
            if(member.getId().equals(this.director_id)){
                return member;
            }
        }
        Member findMember = new Member();
        findMember.setMemberName("부서장이 없음");
        return findMember;
    }
}
