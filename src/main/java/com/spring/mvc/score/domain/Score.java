package com.spring.mvc.score.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Score {

    //클라이언트가 전달한 데이터
    private String name;
    private int kor, eng, math;

    //자체 생성 데이터
    private int stuNum;  //학번
    private int total;   //총점
    private double average; //평균

    //누적되는 순차번호를 만들기 위함
    private static int seq;

    public Score() {
       // System.out.println("Score 객체 생성!!!");
        this.stuNum = ++seq;
        //static 없으면 모든 학생이 1번
    }





}//end class
