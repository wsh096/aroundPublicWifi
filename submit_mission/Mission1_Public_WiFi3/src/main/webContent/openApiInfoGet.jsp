<%@page import="com.module.openApi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
h1{display: flex;
 justify-content:center;
}</style>
</head>
<body>
<%
	openApi con = new openApi();
	int a = 0; 	
	if(a==0){
    con.apicall();
    boolean flag = true;
    if(flag){a +=1;}
	}
	
	
%>
<% 

%>
<h1> <%=con.maxnum%>개의 Wifi 정보를 정상적으로 저장하였습니다.</h1>
<p style="text-align: center;"><a href=./index.jsp>홈으로 가기</a></p>

</body>
</html>