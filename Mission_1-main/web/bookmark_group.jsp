<%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-20
  Time: 오후 4:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹</title>
</head>
<body>
<h1>북마크</h1>
    <%@include file="menu.jsp"%>
    <button onclick="location.href='bookmark_group_add.jsp'">북마크 그룹 이름 추가</button>
    <%@include file="bookmark_group_table.jsp"%>
</body>
</html>
