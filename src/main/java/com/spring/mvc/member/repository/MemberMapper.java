package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    //회원 가입 기능
    boolean register(Member member);

    //아이디 중복체크 ///1이면 중복
    int isDuplicate(String targetKeyword);

    //이메일 중복체크
    int isDuplicate2(String targetKeyword);

    //단일 (1명)회원 정보 조회 기능
    Member getUser(String account);







}//
