package com.spring.mvc.reply.repository;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.reply.domain.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 입력
    boolean save(Reply reply);

    //댓글 수정
    boolean update(Reply reply);

    //댓글 삭제
    boolean delete(Long replyNo);

    //댓글 개별 조회
    Reply read(Long replyNo);

    //댓글 목록 조회
    ////3번글 댓글 목록
    List<Reply> getList(Long boardNo);


    /*
    *  - 마이바티스 매퍼는 기본적으로 1개의 매개 값만 받을 수 있음
    *  - @Param을 통해 여러개를 처리할 수 있음
    * */
    List<Reply> getList(@Param("boardNo") Long boardNo,
                        @Param("page") Page page);

    //총 댓글 수 조회
    int getCount(Long boardNo);

}
