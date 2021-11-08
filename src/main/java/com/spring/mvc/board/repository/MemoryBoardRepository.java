package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class MemoryBoardRepository implements BoardRepository{

    //메모리 저장소
    private Map<Long, Board> boardMap = new HashMap<>();

    public MemoryBoardRepository() {
        boardMap.put(1L,new Board("개그맨","알았다","아라비안나이트"));
        boardMap.put(2L,new Board("뽀로로","노는게","제일좋아"));
        boardMap.put(3L,new Board("Jack","Hey","hello"));
        boardMap.put(4L,new Board("Steven","열정","에프티아일랜드"));
        boardMap.put(5L,new Board("말썽쟁이","씨앤블루","싹둑"));
    }

    @Override
    public List<Board> getArticles() {
        List<Board> boardList = new ArrayList<>();
        for (Long boardNo : boardMap.keySet()) {
            Board board = boardMap.get(boardNo);
            boardList.add(board);
        }
        return boardList;
    }

    @Override
    public Board getContent(Long boardNo) {
        Board board =  boardMap.get(boardNo);
        log.info("board" + board);
        return board;
    }

    @Override
    public boolean insert(Board board) {
        boardMap.put(board.getBoardNo(), board);
        return false;
    }

    @Override
    public boolean delete(Long boardNo) {
        boardMap.remove(boardNo);
        return false;
    }

    @Override
    public boolean update(ModBoard board) {

        // 1. 해시맵에 저장되어 있는 게시물 객체를 꺼낸다
        Board target = boardMap.get(board.getBoardNo());
        // 2. 해당 객체의 데이터들을 ModBoard의 값으로 수정
        target.setWriter(board.getWriter());
        target.setTitle(board.getTitle());
        target.setContent(board.getContent());
        return false;
    }




}//end class
