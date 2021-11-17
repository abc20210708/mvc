package com.spring.mvc.reply.controller;

import com.spring.mvc.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

}//end class
