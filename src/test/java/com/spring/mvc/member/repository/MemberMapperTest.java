package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Auth;
import com.spring.mvc.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired MemberMapper memberMapper;

    @Test
    @DisplayName("비밀번호가 인코딩 된 상태로 회원가입에 성공해야 한다.")
    void regTest() {
        //원본 비밀번호
        String rawPassword = "def1234!";

        //비밀번호를 암호화 인코딩하는 객체 생성
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //암호화된 비밀번호
        String encodePassword =  encoder.encode(rawPassword);

        Member member = Member.builder()
                .account("def1234")
                .email("def111@gamil.com")
                .name("박영희")
                .password(encodePassword)
                .Auth(Auth.COMMON)
                .build();

        /*
        member.setAccount("abc1234");
        member.setPassword("abc1234!");*/
        memberMapper.register(member);

    }

    @Test
    @DisplayName("로그인 검증을 정확하게 수행해야 한다.")
    void loginTest() {
        //로그인 시도
        String inputId = "def1234";
        //로그인 시도 비밀번호
        String inputPw = "def1234!";

        //로그인 시도 아이디를 기본으로 회원 정보 조회
        Member member = memberMapper.getUser(inputId);
        ///가입 되지 않으면 null, 가입되면 멤버 객체

        if (member != null) { //회원 가입을 했음!
            //디비에 저장된 비번 알아오기
            String dbPw = member.getPassword();

            ///비번 비교할 땐
            //암호화된 비번을 평문으로 디코딩 후 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(inputPw, dbPw)) {
                System.out.println("로그인 성공!");
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
            }

        } else  { //회원 가입을 안 함
            System.out.println("회원 가입된 아이디가 아닙니다.");
        }

    }

    @Test
    @DisplayName("아이디, 이메일 중복확인에 성공해야 한다.")
    void duplicateTest() {

        String inputId = "def1234";

        int result = memberMapper.isDuplicate(inputId);
        assertEquals(1, result);
        ///나는 result가 1이라고 기대
        ///테스트가 통과되면 1
       // assertTrue(result == 1);

        String inputEmail = "def111@gamil.com";
        int result2 = memberMapper.isDuplicate2(inputEmail);
        assertTrue(result2 == 1);

    }

}