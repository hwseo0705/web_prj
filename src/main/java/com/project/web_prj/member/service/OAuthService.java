package com.project.web_prj.member.service;

// SNS 로그인 통신 처리 담당
public interface OAuthService {

    /**
     * 엑세스 토큰 발급 메서드
     * @param authCode - 인증 서버가 발급한 인가 코드
     * @return 엑세스 토큰
     * @throws Exception - 통신 에러
     * */
    String getAccessToken(String authCode) throws Exception;
    
}
