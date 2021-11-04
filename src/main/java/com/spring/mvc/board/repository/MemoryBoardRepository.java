package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class MemoryBoardRepository implements BoardRepository{

    //메모리 저장소
    private Map<Long, Board> boardMap = new HashMap<>();

    @Override
    public List<Board> getArticles() {
        return null;
    }

    @Override
    public Board getContent(int boardNo) {
        return null;
    }

    @Override
    public boolean insert(Board board) {
        return false;
    }

    @Override
    public boolean delete(int boardNo) {
        return false;
    }

    @Override
    public boolean update(Board board) {
        return false;
    }
}//end class
