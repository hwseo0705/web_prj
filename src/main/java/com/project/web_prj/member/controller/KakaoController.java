package com.project.web_prj.member.controller;

import com.project.web_prj.member.domain.OAuthValue;
import com.project.web_prj.member.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.project.web_prj.member.domain.OAuthValue.*;

@Controller
@Log4j2
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/kakao-test")
    public void kakaoTest(Model model) {
        log.info("forwarding kakao-test.jsp");
        model.addAttribute("appKey", KAKAO_APP_KEY);
        model.addAttribute("redirectUri", KAKAO_REDIRECT_URI);
    }

    // 카카오 인증서버가 보내준 인가코드를 받아서 처리할 메서드
    @GetMapping(KAKAO_REDIRECT_URI)
    public String kakaoLogin(String code) throws Exception {
        log.info("{} GET!! code - {}", KAKAO_REDIRECT_URI, code);

        // 인가 코드를 통해 엑세스 토큰 발급 받기
        // 우리 서버에서 카카오 서버로 통신을 해야함
        String accessToken = kakaoService.getAccessToken(code);

        return "";
    }
}
