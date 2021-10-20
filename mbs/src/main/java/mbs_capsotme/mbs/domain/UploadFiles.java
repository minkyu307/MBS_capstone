package mbs_capsotme.mbs.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UploadFiles {

    @Id
    private String uuid;

    private String fileName;
//    확장자
    private String extension;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
