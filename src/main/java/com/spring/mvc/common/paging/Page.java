package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//페이징 정보 객체
@Setter @Getter @ToString
public class Page {

    private int pageNum; //페이지 번호
    private int amount; //한 페이지당 게시물 수

    private String type; //검색 조건
    private String keyword; //검색어


    public Page() {
        this.pageNum = 1;
        this.amount = 10;
        this.keyword = "";
    }

    public void setPageNum(int pageNum) {
        if (pageNum < 1) {
            this.pageNum = 1;
            return;
        }
        this.pageNum = pageNum;
    }

    public void setAmount(int amount) {
        if (amount <= 0 || amount > 100) {
            this.amount = 10;
            return;
        }
        this.amount = amount;
    }
}//end class
