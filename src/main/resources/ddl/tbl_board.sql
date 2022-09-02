-- ORACLE

CREATE SEQUENCE seq_board;

DROP TABLE board;
CREATE TABLE board (
    board_no NUMBER(10),
    writer VARCHAR2(20) NOT NULL,
    title VARCHAR2(200) NOT NULL,
    content VARCHAR2(2000),
    view_cnt NUMBER(10) DEFAULT 0,
    reg_date DATE DEFAULT SYSDATE,
    CONSTRAINT pk_board PRIMARY KEY (board_no)
);

SELECT * FROM board;

-- MariaDB
CREATE TABLE tbl_board (
    board_no INT(10) AUTO_INCREMENT,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    view_cnt INT(10) DEFAULT 0,
    reg_date DATETIME DEFAULT current_timestamp,
    CONSTRAINT pk_tbl_board PRIMARY KEY (board_no)
);