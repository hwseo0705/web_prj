package com.project.web_prj.board.domain;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor // Necessary
public class Board {

    // Table Column Fields
    private Long boardNo;
    private String writer;
    private String title;
    private String content;
    private Long viewCnt;
    private Date regDate;
    private String account;

    // Custom Table Fields
    private String shortTitle;
    private String prettierDate;
    private boolean newArticle; // 신규 게시물 여부
    private int replyCount; // 댓글수

    private List<String> fileNames; // 첨부파일들의 이름 목록

    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getLong("board_no");
        this.writer = rs.getString("writer");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.viewCnt = rs.getLong("view_cnt");
        this.regDate = rs.getTimestamp("reg_date");
    }
}
