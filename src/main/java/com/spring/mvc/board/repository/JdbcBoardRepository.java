package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("br")
@Log4j2
public class JdbcBoardRepository implements BoardRepository {

    //DB 접속 정보 설정
    private String id = "spring4";
    private String pw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driver = "oracle.jdbc.driver.OracleDriver";


    @Override
    public List<Board> getArticles() {

        List<Board> boardList = new ArrayList<>();

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);

            String sql = "SELECT * FROM board ORDER BY board_no DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                boardList.add(new Board(rs));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return boardList;
    }

    @Override
    public Board getContent(Long boardNo) {

        String sql = "SELECT * FROM board WHERE board_no=?";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, boardNo);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Board(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;///조회 실패
    }

    @Override
    public boolean insert(Board board) {

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);

            String sql = "INSERT INTO board (board_no, writer, title, content)" +
                    "VALUES (seq_board.nextval, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, board.getWriter());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());

            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long boardNo) {

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);

            String sql = "DELETE FROM board WHERE board_no=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, boardNo);

            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(ModBoard board) {

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, id, pw);

            String sql = "UPDATE board SET writer=?, title=?, content=? WHERE board_no=? " ;
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, board.getWriter());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());
            pstmt.setLong(4, board.getBoardNo());

            return pstmt.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}//end class
