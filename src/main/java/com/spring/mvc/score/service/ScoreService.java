package com.spring.mvc.score.service;

import com.spring.mvc.score.domain.Grade;
import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

//책임: 컨트롤러와 저장소 사이의 중간 데이터 처리 담당
@Service
@RequiredArgsConstructor
public class ScoreService {

    public final ScoreMapper scoreMapper;

   /* private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(
            @Qualifier("ssr") ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }*/

    //저장처리 중간 로직
    public void register(Score score) {
        score.calcTotal();
        scoreMapper.save(score);
    }

    //전체조회 중간처리
    public List<Score> getList() {
        List<Score> scoreList = scoreMapper.findAll();
        if (scoreList != null) {
            //이름에 마킹처리
            for (Score score : scoreList) {
                //이름 빼오기
                String name = score.getName();
                //성 뽑기
                String family = String.valueOf(name.charAt(0));
                //성을 제외한 이름 수
               // int length = name.length() - 1;
                for (int i = 0; i < name.length() - 1; i++) {
                    family += "*";
                }
                score.setMarkName(family);
            }
             return scoreList;
        } else {
            return Collections.emptyList();//빈 리스트 리턴
        }

    }

    //삭제 중간 처리
    public void remove(int stuNum) {
        scoreMapper.remove(stuNum);
    }

    //상세 조회 중간 처리
    public Score getScore(int stuNum) {
       Score score =  scoreMapper.findOne(stuNum);
       //중간 처리
        //평균 뽑기
        double avg = score.getAverage();
        if (avg >= 90) {
            score.setGrade(Grade.A);
        } else if(avg >= 80) {
            score.setGrade(Grade.B);
        } else if(avg >= 70) {
            score.setGrade(Grade.C);
        } else if(avg >= 60) {
            score.setGrade(Grade.D);
        } else {
            score.setGrade(Grade.F);
        }
       return score;
    }
}//end class
