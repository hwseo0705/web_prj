package com.project.web_prj.board.domain;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor // Necessary
public class Board {

    // Table Column Fields
    private Long boardNo;
    private String writer;
    private  String title;
    private  String content;
    private Long viewCnt;
    private Date regDate;

    // Custom Table Fields
    private String shortTitle;
    private String prettierDate;

    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getLong("board_no");
        this.writer = rs.getString("writer");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.viewCnt = rs.getLong("view_cnt");
        this.regDate = rs.getTimestamp("reg_date");
    }
}
