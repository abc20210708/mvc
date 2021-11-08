package com.spring.mvc.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

///수정할 때 필요한 데이터들
@Getter @Setter @ToString
public class ModBoard {

    private Long boardNo;
    private String writer;
    private String title;
    private String content;


}//end class

