package com.spring.mvc.common.paging;

import lombok.Getter;

//페이지 생성정보를 만들어주는 객체
@Getter
public class PageMaker {

    //한 화면에 배치할 페이지 수
    ///prsf
    private static final int PAGE_COUNT = 5;

    private int beginPage, endPage;
    private boolean prev, next;

    private Page page;
    private int totalCount; //총 게시물 수


    public PageMaker(Page page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;

        makePageInfo();
    }

    //페이지 정보를 만드는 메서드
    private void makePageInfo() {
        //끝 페이지 계산
        // 올림 (현재 보고 있는 페이지 넘버 / 한 화면에 보여질 페이지 수) * 한 화면에 보여질 페이지 수
        this.endPage = (int)Math.ceil(page.getPageNum() / (double)PAGE_COUNT) * PAGE_COUNT;

        //시작 페이지 계산
        this.beginPage = endPage - PAGE_COUNT + 1;

        /*
        - 총 게시물 수가 284개이고 한 화면 10페이지씩 보여주고 있다면
        구간은 1~10 페이지 구간: 게시물 100개
            11~20 페이지 구간 : 게시물 100개 //200개
            21~30 페이지 구간: 게시물 84개
            이 마지막 구간에서는 21~29로 바뀌어야 함
            따라서 마지막 구간에는 보정이 들어갈 수도 있음

            -보정 공식 : 올림값 ( 총 게시물 수 / 한 페이지당 보여줄 게시물 수)
        * */
        int realEnd = (int)Math.ceil(totalCount / (double)page.getAmount());

        //보정은 마지막 페이지 구간에서만 일어남
        if (realEnd < endPage) {
            this.endPage = realEnd;
        }

        //이전 버튼 활성화 여부
        this.prev = this.beginPage > 1;

        //다음 버튼 활성화 여부
        this.next = endPage < realEnd;

    }

}//end class
