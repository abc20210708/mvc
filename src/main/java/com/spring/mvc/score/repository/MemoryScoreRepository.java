package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository ///자바 콩들..
public class MemoryScoreRepository implements ScoreRepository{

    //실제 메모리 저장소
    private Map<Integer, Score> scoreMap = new HashMap<>();

    @Override
    public boolean save(Score score) {
        scoreMap.put(score.getStuNum(), score);
        log.info(scoreMap);
        return true;
    }

    @Override
    public List<Score> findAll() {
        List<Score> scores = new ArrayList<>();
        for (Integer stuNum : scoreMap.keySet()) {
            Score score = scoreMap.get(stuNum);
            scores.add(score);
        }
        return scores;
    }

    @Override
    public Score findOne(int stuNum) {
        return null;
    }

    @Override
    public boolean remove(int stuNum) {
        return false;
    }
}//end
