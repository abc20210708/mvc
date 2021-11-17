package com.spring.mvc.reply.repository;

import com.spring.mvc.reply.domain.Reply;
import org.apache.ibatis.annotations.Mapper;

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

}
