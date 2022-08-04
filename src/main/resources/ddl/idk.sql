truncate table tbl_reply;

truncate table file_upload;

DELETE FROM tbl_board;

SELECT * FROM tbl_board;
SELECT * FROM tbl_reply;

ALTER TABLE tbl_board ADD account VARCHAR2(50) NOT NULL;
ALTER TABLE tbl_reply ADD account VARCHAR2(50) NOT NULL;

UPDATE tbl_member SET auth='ADMIN'
WHERE account = 'admin';
commit;

ALTER TABLE tbl_member ADD session_id VARCHAR2(200) DEFAULT 'none';
ALTER TABLE tbl_member ADD limit_time DATE;
