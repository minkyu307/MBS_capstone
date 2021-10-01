package mbs_capsotme.mbs.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Board {

    @Id
    @GeneratedValue
    private Long id;
    private String createTime;
    private String lastModifiedTime;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
