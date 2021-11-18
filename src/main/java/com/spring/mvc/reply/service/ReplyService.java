package com.spring.mvc.reply.service;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.common.paging.PageMaker;
import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;

    /*//댓글 목록 조회 중간처리
    public List<Reply> getList(Long boardNo) {
        return replyMapper.getList(boardNo);

        *//*List<Reply> replies = replyMapper.getList(boardNo);
        ///////////
        return replies;*//*
    }*/

    //댓글 목록 조회
    public Map<String, Object> getList(Long boardNo, Page page) {
        PageMaker maker
                = new PageMaker(page, getCount(boardNo));

        Map<String, Object> replyMap = new HashMap<>();
        replyMap.put("replyList", replyMapper.getList(boardNo, page));
        replyMap.put("maker", maker);

        return replyMap;
    }
    //댓글 총 개수 조회
    public int getCount(Long boardNo) {
        return replyMapper.getCount(boardNo);
    }
    //댓글 개별 조회
    public Reply get(Long replyNo) {
        return replyMapper.read(replyNo);
    }

    //댓글 쓰기 중간 처리
    public boolean write(Reply reply) {
        return replyMapper.save(reply);
    }

    //댓글 수정 중간 처리
    public boolean modify(Reply reply) {
        return replyMapper.update(reply);
    }

    //댓글 삭제 중간 처리
    public boolean remove(Long boardNo) {
        return replyMapper.delete(boardNo);
    }



}//end class
