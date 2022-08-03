package com.project.web_prj.member.repository;

import com.project.web_prj.member.domain.Auth;
import com.project.web_prj.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper mapper;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void registerTest() {
        Member m = new Member();
        m.setAccount("apple123");
        m.setPassword("12345");
        m.setName("Apple");
        m.setEmail("apple@gmail.com");
        m.setAuth(Auth.ADMIN);

        boolean flag = mapper.register(m);

        assertTrue(flag);
    }

    @Test
    @DisplayName("비밀번호가 암호화인코딩 되어야 한다.")
    void encodePasswordTest() {

        // 인코딩 전 비밀번호
        String rawPassword = "ddd5555";

        // 인코딩을 위한 객체 생성
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 인코딩 후 비밀번호
        String encodePassword = encoder.encode(rawPassword);

        System.out.println("rawPassword = " + rawPassword);
        System.out.println("encodePassword = " + encodePassword);
    }

    @Test
    @DisplayName("회원가입에 비밀번호가 인코딩 된 상태로 성공해야 한다.")
    void registerTest2() {
        Member m = new Member();
        m.setAccount("peach123");
        m.setPassword(new BCryptPasswordEncoder().encode("ppp4566"));
        m.setName("Peach");
        m.setEmail("peach@gmail.com");
        m.setAuth(Auth.ADMIN);

        boolean flag = mapper.register(m);

        assertTrue(flag);
    }

    @Test
    @DisplayName("특정 계정명으로 회원을 조회해야 한다.")
    void findUserTest() {
        // given
        String account = "peach123";

        // when
        Member member = mapper.findUser(account);

        // then
        System.out.println("member = " + member);
        assertEquals("Peach", member.getName());
    }

    @Test
    @DisplayName("특정 계정명으로 회원을 조회할 수 없어야 한다.")
    void findUserTest2() {
        // given
        String account = "peach";

        // when
        Member member = mapper.findUser(account);

        // then
        assertNull(member);
    }

    @Test
    @DisplayName("아이디를 중복확인 할 수 있다.")
    void checkAccountTest() {
        // given
        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("type", "account");
        checkMap.put("value", "peach123");

        // when
        int flagNumber = mapper.isDuplicate(checkMap);

        // then
        assertEquals(1, flagNumber);
    }

    @Test
    @DisplayName("이메일을 중복확인 할 수 있다.")
    void checkEmailTest() {
        // given
        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("type", "email");
        checkMap.put("value", "peach@gmail.com");

        // when
        int flagNumber = mapper.isDuplicate(checkMap);

        // then
        assertEquals(1, flagNumber);
    }
}