package com.spring.mvc.member.controller;

import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    //회원가입 form 요청
    @GetMapping("/join")
    public String join() {
        return "member/join";
    }


    //아이디 중복확인 비동기 통신요청
    @GetMapping("/check")
    @ResponseBody ///직접 데이터 보낼 때
    public boolean check(String checkId) {
        log.info("/check 비동기 요청 GET!" + checkId);
        return memberService.isDuplicate(checkId);
    }

    //이메일 중복확인 비동기 통신요청
    @GetMapping("/check2")
    @ResponseBody ///직접 데이터 보낼 때
    public boolean check2(String checkEmail) {
        log.info("/check 비동기 요청 GET!" + checkEmail);
        return memberService.isDuplicate2(checkEmail);
    }

    //회원가입 요청처리
    @PostMapping("/member/sign-up")
    public String signUp(Member member) {
        log.info("/member/sign-up POST! " + member);
        //서비스 위임
        memberService.signUp(member);
        return "redirect:/";
    }

}//end class
