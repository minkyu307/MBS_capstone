package mbs_capsotme.mbs.controller;

import lombok.Getter;
import lombok.Setter;
import mbs_capsotme.mbs.domain.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberForm {
    private Long id;
    private String login_id;
    private String password;
    private LocalDateTime createTime;
    private String memberName;
    private String address;
    private String email;
    private Status loginStatus;
    private String position;
    private String phoneNumber;
    private String departmentName;
}
