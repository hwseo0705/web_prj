package com.project.web_prj.member.service;

import com.project.web_prj.member.domain.Auth;
import com.project.web_prj.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService service;

    @Test
    @DisplayName("평문 비밀번호로 회원가입하면 암호화 되어 저장한다.")
    void signUpTest() {
        Member m = new Member();
        m.setAccount("banana");
        m.setPassword("bbb1234");
        m.setName("Banana");
        m.setEmail("banana@gmail.com");
        m.setAuth(Auth.COMMON);

        service.signUp(m);
    }

    @Test
    @DisplayName("중복된 아이디를 전달하면 true가 나와야 한다.")
    void checkAccountServiceTest() {
        // given
        String account = "banana";

        // when
        boolean flag = service.checkSignUpValue("account", account);

        // then
        assertTrue(flag);
    }

}
