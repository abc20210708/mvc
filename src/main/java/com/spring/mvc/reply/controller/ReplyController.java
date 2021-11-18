package com.spring.mvc.reply.controller;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    /*
        - 댓글 목록 요청 : /api/v1/reply   GET
        - 댓글 쓰기 요청 : /api/v1/reply   POST
        - 댓글 수정 요청 : /api/v1/reply/3  PUT
        - 댓글 삭제 요청 : /api/v1/reply/14 DELETE
    * */

    // 댓글 목록 요청 처리
    @GetMapping("")
    public Map<String, Object> list(Long boardNo, Page page) {
        log.info("/api/v1/reply GET!");
        Map<String, Object> replies = replyService.getList(boardNo, page);
        return replies;
    }

    //댓글 등록 요청 처리
    @PostMapping("")
    public String create(@RequestBody Reply reply) { //@RequestParam 생략
        log.info("/api/v1/reply POST!");
        return replyService.write(reply) ? "insert-success" : "insert-fail";
    }

    //댓글 삭제 요청 처리
    @DeleteMapping("/{rno}")
    public String remove(@PathVariable("rno") Long replyNo) {
        log.info("/api/v1/reply DELETE! " + replyNo);
        return replyService.remove(replyNo) ? "delete-success" : "delete-fail";
    }

    //댓글 수정 요청
    @PutMapping("/{rno}")
    public String modify(@PathVariable("rno") Long replyNo, //주소창에서 긁어와서
                         @RequestBody Reply reply) { //JSON
        log.info("/api/v1/reply PUT!");
        reply.setReplyNo(replyNo);
        return replyService.modify(reply) ? "modify-success" : "modify-fail";
    }

}//end class
