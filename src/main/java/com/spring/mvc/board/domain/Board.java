package com.spring.mvc.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Setter @Getter @ToString
public class Board {

    private Long boardNo; //글번호
    private String writer; //작성자
    private String title; //글제목
    private String content; //글내용
    private Date regDate; //작성 시간
    private int viewCnt; //조회수

    private String regDateStr; //포맷팅된 날짜 문자열

    private static long seq;

    public Board() {
        this.boardNo = ++seq;
    }

    public Board(String writer, String title, String content) {
        this();
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getLong("board_no");
        this.writer = rs.getString("writer");
        this.title = rs.getString("title");
        this.content = rs.getString("content");


    }

}//end class
