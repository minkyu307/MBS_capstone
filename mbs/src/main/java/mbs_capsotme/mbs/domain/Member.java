package mbs_capsotme.mbs.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String login_id;
    private String password;
    private String createTime;
    private String memberName;
    private String address;
    private String email;
    private String sessionId;

    @Enumerated(EnumType.STRING)
    private Status loginStatus;

    private String position;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Memo> memos = new ArrayList<>();

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", login_id='" + login_id + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", memberName='" + memberName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", loginStatus=" + loginStatus +
                ", position='" + position + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", department=" + department +
                ", boards=" + boards +
                ", memos=" + memos +
                '}';
    }
}
