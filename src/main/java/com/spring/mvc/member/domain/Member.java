package com.spring.mvc.member.domain;


import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder ///원하는 것만 채워서 전송할 수 있음!
public class Member {

    private String account;
    private String password;
    private String name;
    private String email;
    private Auth auth; //권한 - ADMIN, COMMON
    private Date regDate; //가입일자



}//end class
