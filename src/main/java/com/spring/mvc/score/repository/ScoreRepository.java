package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;

import java.util.List;

//성적 정보를 저장, 조회, 수정, 삭제하는 역할을 가진 인터페이스
public interface ScoreRepository {

    //점수 저장
    boolean save(Score score);
    /// 잘 저장하면 t

    //전체 점수 정보 조회
    List<Score> findAll();

    //개별 점수 정보 조회
    Score findOne(int stuNum);

    //점수 삭제
    boolean remove(int stuNum);

}//end
