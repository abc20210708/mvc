package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import com.spring.mvc.board.repository.BoardMapper;
import com.spring.mvc.common.paging.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    /*private final BoardRepository boardRepository;

    @Autowired
    public BoardService(
            @Qualifier("bbr") BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }*/

    //게시물 목록 중간 처리
    public List<Board> getList(Page page) {
        List<Board> articles =  boardMapper.getArticles(page);

        //신규 게시물 new 마킹 처리
        return judgeNewArticle(articles);

      /*  //역정렬
        List<Board> sortedList = new ArrayList<>();
        for (int i = articles.size() - 1; i >= 0; i--) {
            sortedList.add(articles.get(i));
        }*/

        //날짜 정보 바꾸기
       /* for (Board article : articles) {
            Date regDate = article.getRegDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm");
            String prettierDate =  sdf.format(regDate);
            article.setRegDateStr(prettierDate);
        }*/



        //return articles;
    }

    private List<Board> judgeNewArticle(List<Board> articles) {
        //해당 리스트에서 게시물 객체를 하나씩 꺼내 작성일자를 추출
        for (Board article : articles) {
            //작성일자
            long regDateTime = article.getRegDate().getTime();
            //현재 날짜 시간
            long nowTime = System.currentTimeMillis();

            // 현재시간 - 작성시간
            long diff = nowTime - regDateTime;

            long limitTime = 20 * 60 * 1000;
            if (diff < limitTime) {
                article.setNewFlag(true);
            }

        }
        return articles;
    }

    //글쓰기 중간처리
    public boolean write(Board board) {

        return boardMapper.insert(board);
    }

    //상세조회 중간처리
    public Board get(Long boardNo) {
        return boardMapper.getContent(boardNo);
    }

    //수정 중간 처리
    public boolean update(ModBoard board) {
        boardMapper.update(board);
        return true;
    }

    public void remove(Long boardNo) {
        boardMapper.delete(boardNo);
    }
}//end class
