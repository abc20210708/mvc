package com.spring.mvc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//역할: 브라우저의 요청을 처리
@Controller
@Log4j2 //로그를 만들어주는 기능
public class BasicController {

    //요청 처리 메서드
//    @RequestMapping(value = "/req/hello", method = RequestMethod.GET)
    @GetMapping("/req/hello")
    public String hello() {
        //비즈니스 로직
        System.out.println("안녕안녕 hello hello");

        //화면 연결 - 2가지 방법
        /*
           1. redirect - 재요청
           2. forward  - 단순한 파일열기
         */
        //forward 시에는 열어야 할 jsp의 경로를 문자열로 입역
     //   return "/WEB-INF/views/test.jsp";
       // return "redirect:/s-login-form";
        return "test";
    }

    //사용자의 요청 URI: /req/ex
    //응답시에 views폴더 아래에 req_ex폴더 아래에 있는 v1.jsp를 열어야함함
    @GetMapping("/req/ex")
    public String ex() {
        //return  "/WEB-INF/views/req_ex/v1.jsp";
        return "req_ex/v1";
    }

    @GetMapping("req/v1")
    public String v1() {
        //System.out.println("/req/v1 GET! ");
        log.info("/req/v1 GET! ");
        return "req_ex/v1";
    }


    @PostMapping("req/v1")
    public String v1Post() {
        log.info("/req/v1 POST! ");
        return "req_ex/v1";
    }


    //요청 파라미터 받기: 클라이언트에서 서버로 전송된 데이터
    // www.abc.com/req/v1?pet=puppy&kind=bulldog
    ///물음표 뒤가 요청 파라미터

    // 1. HttpServletRequest 객체 활용하기
    @PostMapping("req/v1")
    public String v1Post(HttpServletRequest request) {
        log.info("/req/v1 POST!");
        String petName = request.getParameter("pet");
        String age = request.getParameter("age");
        int ageNum = Integer.parseInt(age);

        log.info("선택한 동물: " + petName);
        log.info(petName + "의 내년 나이는 " + (ageNum + 1) + "살 입니다.");
        return "req_ex/v1";
    }

    //2. @RequestParam 이용하기
    @PostMapping("/req/v2")
    public String v2(
            @RequestParam("pet") String pet1, //클라이언트가 전달한 pet을 pet1에 넣을거야
            ///@RequestParam 소괄호 안에 값을 제대로 써줘야해요.
            ///지역변수에서 이미 사용하면  @RequestParam 붙여서 사용 , 따로 얘기 해줘야함!
            ///age처럼 같은 아이들은  @RequestParam 생략이 가능함
            int age) {

        String pet = "하하하";
        log.info(String.format("%s의 작년 나이는 %d살 입니다.", pet1, age-1));

        return "req_ex/v1";
    }

    //3. 커맨드 객체 활용하기
    @PostMapping("/req/v3")
    public String v3(Pet petInfo) {
         log.info(petInfo);
        return "req_ex/v1";
    }





}//end class