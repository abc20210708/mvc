<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>

    <div id="pet-info">
        <h1> # ${pet.pet} 정보!!</h1>
        <p>
            우리 애완동물 이름은 ${pet.pet}이구요~~ <br>
            나이는 ${pet.age}살 입니다. 그리고 주인인 제 이름은 ${pet.master}이구요~ <br>
            ${pet.pet}의 성별은 ${pet.gender}입니다.
        </p>

        <h2>* ${pet.pet}이(가) 좋아하는 것!</h2>
        <ul>
            <c:forEach var="f" items="${hobby}">
                <span> ${f}</span> 
             </c:forEach>

        </ul>
    </div>

</body>
</html>