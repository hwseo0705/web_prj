package com.project.web_prj.board.controller;

import com.project.web_prj.board.domain.Board;
import com.project.web_prj.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 게시물 목록 요청
    @GetMapping("/list")
    public String list(Model model) {
        log.info("controller request /board/list GET!");
        List<Board> boardList = boardService.findAllService();
        log.debug("return data - {}", boardList);

        model.addAttribute("bList", boardList);
        return "board/board-list";
    }

    // 게시물 상세 조회 요청
    @GetMapping("/content/{boardNo}")
    public String content(@PathVariable Long boardNo, Model model, HttpServletResponse response, HttpServletRequest request) {
        log.info("controller request /board/content GET! - {}", boardNo);
        Board board = boardService.findOneService(boardNo, response, request);
        log.info("return data - {}", board);

        model.addAttribute("b", board);
        return "board/board-detail";
    }

    // 게시물 쓰기 화면 요청
    @GetMapping("/write")
    public String write() {
        log.info("controller request /board/write GET!");
        return "board/board-write";
    }

    // 게시물 등록 요청
    @PostMapping("/write") // RequestBody for RESTful
    public String write(/*@RequestBody*/ Board board, RedirectAttributes ra) {
        log.info("controller request /board/write POST! - {}", board);
        boolean flag = boardService.saveService(board);
        // 게시물 등록이 성공하면 클라이언트에 성공 메시지 전송
        // if (flag) model.addAttribute("msg", "reg-success"); // redirect 되는순간 reg-success 데이터가 날라감
        if (flag) ra.addFlashAttribute("msg", "reg-success");

        return flag ? "redirect:/board/list" : "redirect:/";
    }

    // 게시물 삭제 요청
    @GetMapping("/delete")
    public String delete(Long boardNo) {
        log.info("controller request /board/delete GET! - {}", boardNo);
        return boardService.removeService(boardNo) ? "redirect:/board/list" : "redirect:/";
    }

    // 게시글 수정 화면 요청
    @GetMapping("/modify")
    public String modify(Long boardNo, Model model, HttpServletResponse response, HttpServletRequest request) {
        log.info("controller request /board/modify GET! - bno: {}", boardNo);
        Board board = boardService.findOneService(boardNo, response, request);
        log.info("article found: {}", board);

        model.addAttribute("board", board);
        return "board/board-modify";
    }

    // 게시글 수정 처리 요청
    @PostMapping("/modify")
    public String modify(Board board) {
        log.info("controller request /board/modify POST! - {}", board);
        return boardService.modifyService(board) ? "redirect:/board/content/" + board.getBoardNo() : "redirect:/";
    }
}