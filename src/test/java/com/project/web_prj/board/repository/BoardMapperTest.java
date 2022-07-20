package com.project.web_prj.board.repository;

import com.project.web_prj.common.paging.Page;
import com.project.web_prj.common.search.Search;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("제목으로 검색된 목록")
    void searchByTitleTest() {
        Search search = new Search(new Page(1, 10), "tc", "글제");
        mapper.findAll2(search).forEach(System.out::println);
    }
}