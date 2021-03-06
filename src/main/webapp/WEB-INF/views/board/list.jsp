<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">

<head>


    <%@ include file ="../include/static-head.jsp" %>


    <style>
        .board-list {
            width: 70%;
            margin: 0 auto;
        }

        .board-list .articles {

            margin: 250px auto 150px;
            border-collapse: collapse;
            font-size: 1.5em;
        }

        /* .board-list .articles tr td,
        .board-list .articles tr th {
            border: 1px solid gray;
            padding: 10px;
        } */

        .board-list .bottom-section {
            display: flex;
            margin-top: -50px;
        }

        .board-list .bottom-section ul {
            flex: 9;
            display: flex;
            justify-content: center;

        }

        .pagination-custom a {
            color: #444 !important;
        }

        .pagination-custom li.active a {
            background: #333 !important;
            color: #fff !important;
        }

        .board-list .btn-write {
            flex: 1;
            /* text-align: right;
            position: relative;
            top: -100px;
            background: orange; */
        }

        .board-list .amount {
            position: absolute;
            top: 15%;
            right: 16%;
        }

        .board-list .amount a {
            width: 80px;
        }

        .board-list .search {
            position: absolute;
            top: 21%;
            left: 16%;
        }

        .board-list .search form {
            display: flex;
        }

        .board-list .search form select {
            flex:1;
            margin-right: 10px;
        }

        .board-list .search form input {
            flex: 2;
        }
    </style>

</head>

<body>


    <div class="wrap">

        <%@ include file ="../include/header.jsp" %>

        <div class="board-list">

            <!-- ????????? ?????? -->
            <section class="search">
                <form action="/board/list" method="get">
                    <select id="search-type" class="form-select" name="type">
                        <option value="title">??????</option>
                        <option value="content">??????</option>
                        <option value="writer">?????????</option>
                        <option value="titleContent">??????+??????</option>
                    </select>
                    <input class="form-control" type="text" name="keyword" value="${pageInfo.page.keyword}">
                    <button class="btn btn-primary" type="submit">
                        <!-- <span class="lnr lnr-magnifier"></span> -->
                        <i class="fas fa-search"></i>
                    </button>
                </form>
            </section>


            <div class="amount">
                <a class="btn btn-danger" href="/board/list?amount=10">10</a>
                <a class="btn btn-danger" href="/board/list?amount=20">20</a>
                <a class="btn btn-danger" href="/board/list?amount=30">30</a>
            </div>




            <table class="table table-dark table-striped table-hover articles">
                <tr>
                    <th>??????</th>
                    <th>?????????</th>
                    <th>??????</th>
                    <th>?????????</th>
                    <th>????????????</th>
                </tr>

                <c:forEach var="b" items="${articles}">
                    <tr>
                        <td>${b.boardNo}</td>
                        <td>${b.writer}</td>
                        <td>
                            ${b.title}
                            <c:if test="${b.newFlag}">
                                <span class="badge rounded-pill bg-danger">new</span>
                            </c:if>
                        </td>
                        <td>${b.viewCnt}</td>
                        <td>
                            <fmt:formatDate value="${b.regDate}" pattern="yyyy??? MM??? dd??? E  HH:mm" />
                            <!-- a ?????? ?????? -->
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <div class="bottom-section">

                <!-- <div>${pageInfo}</div> -->

                <ul class="pagination pagination-lg pagination-custom">

                    <c:if test="${pageInfo.prev}">
                        <li class="page-item"><a class="page-link"
                                href="/board/list?pageNum=${pageInfo.beginPage -1}&amount=${pageInfo.page.amount}&type=${pageInfo.page.type}&keyword=${pageInfo.page.keyword}">prev</a>
                        </li>
                    </c:if>

                    <c:forEach var="i" begin="${pageInfo.beginPage}" end="${pageInfo.endPage}" step="1">
                        <li class="page-item"><a class="page-link"
                                href="/board/list?pageNum=${i}&amount=${pageInfo.page.amount}&type=${pageInfo.page.type}&keyword=${pageInfo.page.keyword}">${i}</a></li>
                    </c:forEach>

                    <c:if test="${pageInfo.next}">
                        <li class="page-item"><a class="page-link"
                                href="/board/list?pageNum=${pageInfo.endPage + 1}&amount=${pageInfo.page.amount}&type=${pageInfo.page.type}&keyword=${pageInfo.page.keyword}">next</a>
                        </li>
                    </c:if>
                </ul>


                <div class="btn-write">
                    <a class="btn btn-outline-danger btn-lg" href="/board/write">?????????</a>
                </div>

            </div>

        </div>

        <script>
            //???????????? ?????? ?????????
            const $table = document.querySelector('.articles');
            $table.addEventListener('click', e => {
                if (!e.target.matches('.articles td')) return;

                //console.log('tr ????????? -' , e.target);

                let bn = e.target.parentElement.firstElementChild.textContent;
                console.log('????????? :' + bn);

                location.href = '/board/content?boardNo=' + bn + '&pageNum=${pageInfo.page.pageNum}' +
                    '&amount=${pageInfo.page.amount}';
            });

            //?????? ????????? ???????????? active ????????? ????????????
            function appendPageActive() {
                //?????? ????????? ????????? ??????
                const curPage = '${pageInfo.page.pageNum}';
                // console.log('???????????????: ', curPage);

                //ul??? li?????? ?????? ???????????? ??? ????????? ?????????(????????? ??????)???
                //?????? ????????? ????????? ????????? ?????? li?????? class = "active" ??????
                const $ul = document.querySelector('.pagination');
                for (let $li of [...$ul.children]) {
                    if ($li.textContent === curPage) {
                        $li.classList.add('active');
                        break;
                    }
                }
            }

            //?????? ?????? ??? select option ??? ??????
            function fixSearchOption() {
                const $select = document.getElementById('search-type');
                for (let $op of [...$select.children] ) {
                    if($op.value === '${pageInfo.page.type}') {
                        $op.setAttribute('selected', 'selected');
                        break;
                    }
                }
            }

            appendPageActive();
            fixSearchOption();

        </script>


        <%@ include file ="../include/footer.jsp" %>

    </div>

</body>

</html>