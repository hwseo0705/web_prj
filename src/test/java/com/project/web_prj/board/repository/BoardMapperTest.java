package com.project.web_prj.board.repository;

import com.project.web_prj.board.domain.Board;
import com.project.web_prj.board.dto.ValidateMemberDTO;
import com.project.web_prj.common.search.Search;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("제목으로 검색된 목록")
    void searchByTitleTest() {
        Search search = new Search("tc", "글제");
        mapper.findAll2(search).forEach(System.out::println);
    }

    @Test
    @DisplayName("특정 게시물에 첨부된 파일경로들을 조회한다.")
    void findFileNamesTest() {
        // given
        Long bno = 323L;

        // when
        List<String> fileNames = mapper.findFileNames(bno);

        // then
        fileNames.forEach(System.out::println);
        assertEquals(1, fileNames.size());
    }

    @Test
    @DisplayName("게시물 번호로 글쓴이의 계정명과 권한정보를 가져와야 한다.")
    void findMemberTest() {
        // given
        Long boardNo = 327L;

        // when
        ValidateMemberDTO member = mapper.findMemberByBoardNo(boardNo);

        // then
        System.out.println("member = " + member);
        assertEquals("peach123", member.getAccount());
        assertEquals("ADMIN", member.getAuth().toString());
    }
}