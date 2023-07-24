<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="spring.servlet.domain.member.Member" %>  <!-- import 역할 -->
<%@ page import="spring.servlet.domain.member.MemberRepository" %> <!-- import 역할 -->
<%
    //request, response 사용 가능
    //spring.servlet.web.servlet.MemberSaveSerlvet에서 쓴 자바 코드 그대로 들고 올 수 있다.
    //즉, JSP는 해당 기호 안에 자바 코드를 담을 수 있다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>저장</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>