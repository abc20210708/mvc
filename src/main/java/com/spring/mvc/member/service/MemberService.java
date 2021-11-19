package com.spring.mvc.member.service;

import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    //아이디 중복 확인 중간 처리
    public boolean isDuplicate(String checkId) {
        return memberMapper.isDuplicate(checkId) == 1; //1이 나오면 중복
    }

    public boolean isDuplicate2(String checkEmail) {
        return memberMapper.isDuplicate(checkEmail) == 1; //1이 나오면 중복
    }


    //회원가입 중간처리 - 비밀번호 인코딩
    public void signUp(Member member) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPw = encoder.encode(member.getPassword());
        member.setPassword(encoderPw);

        memberMapper.register(member);
    }
}//end class
