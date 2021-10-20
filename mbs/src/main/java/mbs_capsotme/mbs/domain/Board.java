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
public class Board {

    @Id
    @GeneratedValue
    private Long id;
    private String createTime;
    private String lastModifiedTime;
    private String contents;
    private String title;
    private int views;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<UploadFiles> uploadFiles = new ArrayList<>();
}
