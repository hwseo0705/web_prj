package com.project.web_prj.board.domain;

import lombok.*;

import java.util.Date;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor // Necessary
public class Board {

    private Long boardNo;
    private String writer;
    private  String title;
    private  String content;
    private Long viewCnt;
    private Date regDate;
}
