package com.spring.mvc.reply.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class Reply {

    private Long replyNo; // 댓글번호
    private String replyText; // 댓글 내용
    private String replyWriter; //댓글 작성자
    private Date replyDate;//작성 잘짜
    private Long boardNo; //원본 글번호



}
