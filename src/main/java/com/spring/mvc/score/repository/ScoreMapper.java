package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper { //구현체 필요 x

    //점수 저장
    boolean save(Score score);
    /// 잘 저장하면 t

    //전체 점수 정보 조회
    List<Score> findAll();

    //개별 점수 정보 조회
    Score findOne(int stuNum);

    //점수 삭제
    boolean remove(int stuNum);

}////
