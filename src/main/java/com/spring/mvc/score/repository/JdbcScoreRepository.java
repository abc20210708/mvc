package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("jr")
@Log4j2
public class JdbcScoreRepository implements ScoreRepository{

    //DB 접속 정보 설정
    private String id = "spring4";
    private String pw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driver = "oracle.jdbc.driver.OracleDriver";


    @Override
    public boolean save(Score score) {

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);

            String sql = "INSERT INTO score VALUES (seq_score.nextval, ?,?,?,?,?,?) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, score.getName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6,score.getAverage());

            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Score> findAll() {

        List<Score> scoreList = new ArrayList<>();

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);

            String sql = "SELECT * FROM score ORDER BY stu_num DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                scoreList.add(new Score(rs));
                /* Score score = new Score(rs);
                scoreList.add(score); ///ctrl + Alt + N */
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    @Override
    public Score findOne(int stuNum) {

        String sql = "SELECT * FROM score WHERE stu_num=? ";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);


            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stuNum);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
               return new Score(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; ///조회 실패
    }

    @Override
    public boolean remove(int stuNum) {

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);

            String sql = "DELETE FROM score WHERE stu_num=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, stuNum);

            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}//end class
