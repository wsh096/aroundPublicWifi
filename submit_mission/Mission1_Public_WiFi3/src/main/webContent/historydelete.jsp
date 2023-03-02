<%@page import="com.db.dao.HistoryDao"%>
<%@page import="com.module.JdbcTemplate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
	@import url("css/basic.css");
</style>
</head>

<body>
	<%
	JdbcTemplate jt = new JdbcTemplate();
	HistoryDao dao = new HistoryDao(jt);
  String ID= request.getParameter("ID");
%>
<script>
  <%
   if(dao.deleteHistory(ID)==1){
    out.write("alert('삭제 되었습니다')");
  }else{
     out.write("alert('오류가 발생했습니다.')");
  }
  %>
</script>
</body>
</html>

<script src="js/tohistory.js"></script>
