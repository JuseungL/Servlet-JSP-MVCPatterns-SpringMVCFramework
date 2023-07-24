<%@ page contentType="text/html;charset=UTF-8" language="java" %> <!-- JSP 파일임을 명시 -->
<html>
<head>
    <title>회원 정보 입력</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post"> <!--action을 다음 넘어걸 jsp의 경로를 찍어줌-->
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>