<%@page import="com.db.dto.HistoryDto"%>
<%@page import="java.util.List"%>
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
	@import url("css/style.css");
</style>
</head>
<h1>위치 히스토리 목록</h1>
<%@ include file="menu.jsp"%>
<body>
	<table id="customers">
	  <thead>	
		<tr>
		  <th>ID</th>
	      <th>X좌표</th>
	      <th>Y좌표</th>
	      <th>조회일자</th>
	      <th>비고</th>
		</tr>
	  </thead>
	  
	  <tbody>
	  	<%
            JdbcTemplate jt = new JdbcTemplate();
	  		HistoryDao history = new HistoryDao(jt);
        
	  		List<HistoryDto> his;
	  		his = history.select();
	  		for(int i = 0 ; i < his.size(); i++){
        %>
        
        <tr>
            <td><%=his.get(i).getId()%></td>
            <td><%=his.get(i).getLat() %></td>
            <td><%=his.get(i).getLnt()%></td>
            <td><%=his.get(i).getDatetime() %></td>
            <td>
                <button onclick='location.href=\"./historydelete.jsp?ID="+his.get(i).getId()+"\"'>삭제</button>
            </td>
        </tr>
        <%
            }
        %>
	  </tbody>	
	</table>
</body>
</html>