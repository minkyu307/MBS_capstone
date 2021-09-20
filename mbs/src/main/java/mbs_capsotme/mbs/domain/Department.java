package mbs_capsotme.mbs.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
}
