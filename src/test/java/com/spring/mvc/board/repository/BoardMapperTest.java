package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// 스프링 컨테이너에서 객체를 주입 받을 수 있음
@SpringBootTest
class BoardMapperTest {

    @Autowired //객체 자동 주입
    BoardMapper boardMapper;

    @Test
    void bulkInsert() {
        for (int i = 1; i <= 300 ; i++) {
            Board board = new Board("사람" + i, "제목제목" + i, "내용내용" + i);
            boardMapper.insert(board);
        }
    }

}//