<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>

    <%@ include file ="../include/static-head.jsp" %>

    <style>

        .write-container {
            width: 70%;
            margin: 200px auto 150px;
            font-size: 1.2em;
            
        }

    </style>

</head>
<body>

    <div class="wrap">


        <%@ include file ="../include/header.jsp" %>

        <div class="write-container">

            <form action="/board/write" method="post" autocomplete="off">


                <input type="hidden" name="account" value="${loginUser.account}">

                <div class="mb-3">
                    <label for="exampleFormControlInput1" class="form-label">작성자</label>
                    <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="이름" name="writer" value="${loginUser.name}" readonly>
                  </div>
                  <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">글제목</label>
                    <textarea name ="title" class="form-control" id="exampleFormControlTextarea1" rows="1" placeholder="제목"></textarea>
                  </div>
                  <div class="mb-3">
                    <label for="exampleFormControlTextarea1" class="form-label">내용</label>
                    <textarea name ="content" class="form-control" id="exampleFormControlTextarea1" rows="10"></textarea>
                  </div>

                  <div class="d-grid gap-2">
                    <button class="btn btn-dark" type="submit">글 작성하기</button>
                    <button id="to-list" class="btn btn-warning" type="button">목록으로</button>
                  </div>

            </form>

        </div>


        <%@ include file ="../include/footer.jsp" %>


    </div>

    <script>
        //목록 버튼 이벤트
        const $toList = document.getElementById('to-list');
        $toList.onclick = e => {
            location.href = '/board/list';
        };
    

    </script>

</body>
</html>