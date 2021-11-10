package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bbr")
@Log4j2
@RequiredArgsConstructor
public class SpringBoardRepository implements BoardRepository{

    //JDBC의 중복 코드를 처리해주는 템플릿
    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Board> getArticles() {
        String sql = "SELECT * FROM board ORDER BY board_no DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Board(rs));
    }

    @Override
    public Board getContent(Long boardNo) {
        String sql = "SELECT * FROM board WHERE board_no = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Board(rs), boardNo);
    }

    @Override
    public boolean insert(Board board) {
        String sql ="INSERT INTO board (board_no, writer, title, content)" +
                "VALUES (seq_board.nextval, ?, ?, ?)";
        return jdbcTemplate.update(sql, board.getWriter(),
                board.getTitle(), board.getContent()) == 1;
    }

    @Override
    public boolean delete(Long boardNo) {
        String sql = "DELETE FROM board WHERE board_no = ?";
        return jdbcTemplate.update(sql, boardNo) == 1;
    }

    @Override
    public boolean update(ModBoard board) {
        String sql = "UPDATE board SET writer=?, title=?, content=? WHERE board_no=? " ;
        return jdbcTemplate.update(sql, board.getWriter(), board.getTitle(),
                board.getContent(), board.getBoardNo()) == 1;
    }
}
