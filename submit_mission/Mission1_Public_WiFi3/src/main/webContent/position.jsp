<%@page import="com.module.openApi"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src = "js/mylocation.js"></script>
</head>

<body>
<form action="./index.jsp" method="get">
LAT: <input type="text" id ="LAT"  name="LAT" value="0.0">, LNT: <input type="text" id ="LNT" name="LNT" value="0.0">
<input type = "button" onclick="myPosition()" value = "내 위치 가져오기"/> 
<input type = "submit" onclick=<%openApi oa = new openApi(); oa.apicall(); %> value = "근처 WIFI 정보 보기"/> 
</form>



</body>

</html>

