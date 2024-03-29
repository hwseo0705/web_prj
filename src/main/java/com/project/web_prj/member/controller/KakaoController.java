package com.project.web_prj.member.controller;

import com.project.web_prj.member.domain.Member;
import com.project.web_prj.member.dto.KakaoUserInfoDTO;
import com.project.web_prj.member.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static com.project.web_prj.member.domain.OAuthValue.KAKAO_APP_KEY;
import static com.project.web_prj.member.domain.OAuthValue.KAKAO_REDIRECT_URI;
import static com.project.web_prj.member.domain.SNSLogin.KAKAO;
import static com.project.web_prj.util.LoginUtils.LOGIN_FLAG;
import static com.project.web_prj.util.LoginUtils.LOGIN_FROM;

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
    public String kakaoLogin(String code, HttpSession session) throws Exception {
        log.info("{} GET!! code - {}", KAKAO_REDIRECT_URI, code);

        // 인가 코드를 통해 엑세스 토큰 발급 받기
        // 우리 서버에서 카카오 서버로 통신을 해야함
        String accessToken = kakaoService.getAccessToken(code);

        // 엑세스 토큰을 통해 사용자 정보 요청 (프로필 사진, 닉네임 등)
        KakaoUserInfoDTO userInfo = kakaoService.getKakaoUserInfo(accessToken);

        // 로그인 처리
        if (userInfo != null) {
            Member member = new Member();
            member.setAccount(userInfo.getEmail());
            member.setName(userInfo.getNickName());
            member.setEmail(userInfo.getEmail());
            session.setAttribute(LOGIN_FLAG, member);
            session.setAttribute("profile_path", userInfo.getProfileImg());
            session.setAttribute(LOGIN_FROM, KAKAO);
            session.setAttribute("accessToken", accessToken);
            return "redirect:/";
        }
        return "redirect:/member/sign-in";
    }

    // 카카오 로그아웃
    @GetMapping("/kakao/logout")
    public String kakaoLogout(HttpSession session) throws Exception {

        // 카카오 로그아웃 처리
        kakaoService.logout((String) session.getAttribute("accessToken"));

        // 우리 서비스 로그아웃
        session.invalidate();

        return "redirect:/kakao-test";
    }
}
