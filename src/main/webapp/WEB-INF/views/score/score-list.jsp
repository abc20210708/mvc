<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- 검색 엔진 최적화(SEO) -->
    <meta name="author" content="Soongu Hong">
    <meta name="description" content="Soon9's Web Site">

    <!-- http://ogp.me -->
    <meta property="og:type" content="website">
    <meta property="og:site_name" content="Soon9's Web studio">
    <meta property="og:title" content="Soon9's Web studio">
    <meta property="og:description" content="Welcome to Soon9's Web studio">
    <meta property="og:image" content="img/visual2.jpg">
    <meta property="og:url" content="http://www.soon9.co.kr">

    <meta property="twitter:card" content="summary">
    <meta property="twitter:site_name" content="Soon9's Web studio">
    <meta property="twitter:title" content="Soon9's Web studio">
    <meta property="twitter:description" content="Welcome to Soon9's Web studio">
    <meta property="twitter:image" content="img/visual2.jpg">
    <meta property="twitter:url" content="http://www.soon9.co.kr">

    <title>Hong Studio</title>

    <!-- 파비콘 넣기 -->
    <link rel="icon" href="siteicon.ico">
    <link rel="apple-touch-icon" href="siteicon.ico">
    <!-- 아이폰 즐겨찾기 아이콘 -->

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- linear icons -->
    <!-- https://linearicons.com/free#cdn -->
    <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

    <!-- animate css || https://animate.style -->
    <link rel="stylesheet" href="css/animate.min.css">

    <!-- slick css 슬라이드 쇼 || https://kenwheeler.github.io/slick/  -->
    <!-- <link rel="stylesheet" href="css/slick.css"> -->

    <!-- custom css 우리가 작성한 css -->
    <link rel="stylesheet" href="/css/main.css">

    <!--custom js-->
    <script src="/js/gnb-event.js" defer></script>

 
<style>

    label {
        display: block;
    }
    .score-list > li {
        margin-bottom: 10px;
    }
    .score-list > li:first-child {
        font-size: 1.2em;
        color: blue;
        font-weight: 700;
        border-bottom: 1px solid skyblue;
    }

    .del-btn {
        width: 10px;
        height: 10px;
        background: red;
        color: #fff;
        border-radius: 5px;
        margin-left: 5px;
        text-decoration: none;
        font-size: 0.7em;
        padding: 6px;
    }
    .del-btn:hover{
        background: orangered;
    }

    section.score {
        padding: 200px 50px 100px;
        font-size: 1.5em;
    }

</style>
</head>
<body>

    <div class="wrap">
         <!-- header -->
        <header class="common">
            <div class="inner-header">
                <h1 class="logo">
                    <a href="#">
                        <img src="/img/logo.png" alt="로고이미지">
                    </a>
                </h1>
                <h2 class="intro-text">Welcome</h2>
                <a href="#" class="menu-open">
                    <span class="menu-txt">MENU</span>
                    <!-- 햄버거버튼 -->
                    <span class="lnr lnr-menu"></span>
                </a>
            </div>

            <nav class="gnb">
                <a href="#" class="close">
                    <span class="lnr lnr-cross"></span>
                </a>
                <ul>
                    <li><a href="/">Home</a></li>
                    <li><a href="/score/list">Score App</a></li>
                    <li><a href="/work">Work</a></li>
                    <li><a href="/hello.jsp">Contact</a></li>
                    <li><a href="/login-form">Login</a></li>
                    <li><a href="/s-login-form">S-Login</a></li>
                </ul>
            </nav>

        </header>
        <!-- //header -->

        <section class="score">
            <h1>시험 점수 등록</h1>
            <form action="/score/register" method="POST">
                <label>
                    # 이름: <input type="text" name="name">
                </label>
                <label>
                    # 국어: <input type="text" name="kor">
                </label>
                <label>
                    # 영어: <input type="text" name="eng">
                </label>
                <label>
                    # 수학: <input type="text" name="math">
                </label>
                <label>
                    <button type="submit">확인</button>
                    <button id="go-home" type="button">홈화면으로</button>
                </label>
            </form>

            <hr>

            <ul class="score-list">
                <li>총 학생 수: ${scores.size()}명</li>

                <c:forEach var="s" items="${scores}">
                <li>
                    # 학번: ${s.stuNum}, 이름: <a href="/score/detail?stuNum=${s.stuNum}">${s.markName}</a>, 국어: ${s.kor}점,
                    영어: ${s.eng}점, 수학: ${s.math}점, 총점: ${s.total}점,
                    평균: ${s.average}점
                    <a class="del-btn" href="/score/delete?stuNum=${s.stuNum}">삭제</a>
                </li>
            </c:forEach>
            </ul>
        </section>
                
        <!-- footer-->
        <footer>
            <ul>
                <li>Hong Studio<br>사업자등록번호:123-12-12345 <br>대표자:홍길동</li>
                <li>
                    <span>A :</span>대전 서구 계룡로 637<br>
                    <span>T :</span>010.1234.1234<br>
                    <span>E :</span>abc123@abc.co.kr<br>
                </li>
                <li> &#xa9 Hong Studio 2021 <br>All Rights Reseved</li>
            </ul>
            <a href="#top" class="go-top"><span class="lnr lnr-arrow-up"></span></a>
        </footer>
        <!-- //footer-->

    </div>
    <script>
        const $ul = document.querySelector(".score-list");



        $ul.addEventListener('click', e => {
            if(!e.target.matches('a.del-btn')) return;    

            e.preventDefault();
            //console.log('클릭이벤트 발동!');

             if(confirm('정말로 삭제하시겠습니까?!')) {
                //삭제 진행 ///score/delete?stuNum=${s.stuNum}이 링크를 활성화 시킴
                //location.href = '/score/list' 하면 여기로 요청이 가는 것
                location.href = e.target.getAttribute('href');
             } else {
                 //삭제취소
                 return;
             }
         
        });

        //홈 화면으로 이벤트
        const $homeBtn = document.getElementById('go-home');
        $homeBtn.onclick = e => {
            location.href = '/';
        };


    </script>

    

</body>
</html>