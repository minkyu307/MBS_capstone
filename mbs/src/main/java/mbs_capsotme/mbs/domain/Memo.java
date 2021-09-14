package mbs_capsotme.mbs.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Memo {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
