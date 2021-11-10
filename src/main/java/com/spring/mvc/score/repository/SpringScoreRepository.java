package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("ssr")
@Log4j2
@RequiredArgsConstructor ///생성자
public class SpringScoreRepository implements ScoreRepository{

    //JDBC의 중복코드를 처리해주는 템플릿
    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO score VALUES (seq_score.nextval, ?,?,?,?,?,?) ";
        return jdbcTemplate.update(sql, score.getName(), score.getKor(), score.getEng(),
                score.getMath(), score.getTotal(), score.getAverage()) == 1;
    }

    @Override
    public List<Score> findAll() {
        String sql = "SELECT * FROM score";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Score(rs));
        };

      /*  List<Score> scoreList
                = jdbcTemplate.query(sql, new ScoreRowMapper());
        return scoreList;*/


    @Override
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM score WHERE stu_num = ?";
        //return jdbcTemplate.query(sql,(rs, rowNum) -> new Score(rs),stuNum ).get(0);
        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> new Score(rs),stuNum );
    }

    @Override
    public boolean remove(int stuNum) {

        String sql = "DELETE FROM score WHERE stu_num = ?";
        return jdbcTemplate.update(sql, stuNum) == 1;

        //return jdbcTemplate.update("DELETE FROM score WHERE stu_num = ?", stuNum) == 1;
    }
}//end class
