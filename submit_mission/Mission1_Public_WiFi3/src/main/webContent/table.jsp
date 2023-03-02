<%@page import="com.db.dao.WifiDao"%>
<%@page import="com.db.dao.HistoryDao"%>
<%@page import="com.module.JdbcTemplate"%>
<%@page import="com.db.dto.HistoryDto"%>
<%@page import="com.db.dto.WifiDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Table 보여주기</title>
<style>
	@import url("css/style.css");
</style>
</head>
<body>

<table id="customers">
	<thead>
 		<tr>
            <th>거리(km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
    </thead>
    <tbody>
    <%
    	JdbcTemplate jt = new JdbcTemplate();
    	HistoryDao history = new HistoryDao(jt); 
    	WifiDao dao = new WifiDao(jt);
    	List<WifiDto> wifi;
    	String LAT = request.getParameter("LAT");
    	String LNT = request.getParameter("LNT");
    	if(LNT == null || LAT == null){//둘 중 하나라도 없으면
    		out.write("<tr><td style= \" text-align: center;\" colspan='17'>위치정보를 입력한 후에 조회해 주세요</td></tr>");
    	}else{//둘 다 있다면
    		
    		history.insertHistory(LAT, LNT);
    		wifi = dao.select(LAT, LNT);
    		
    	if(wifi.isEmpty()){
    		for(WifiDto w : wifi){
    			out.write("<tr>");
    			out.write("<td>" + w.getDistance().substring(0,8) + "</td>");
    			out.write("<td>" + w.getMerNo() + "</td>");
    			out.write("<td>" + w.getGu() + "</td>");
    			out.write("<td>" + w.getWifiName() + "</td>");
    			out.write("<td>" + w.getArr1() + "</td>");
    			out.write("<td>" + w.getArr2() + "</td>");
    			out.write("<td>" + w.getFloor() + "</td>");
    			out.write("<td>" + w.getInstallAgency() + "</td>");
    			out.write("<td>" + w.getInstallType() + "</td>");
    			out.write("<td>" + w.getMangType() + "</td>");
    			out.write("<td>" + w.getInstallYear() + "</td>");
    			out.write("<td>" + w.getWhere() + "</td>");
    			out.write("<td>" + w.getWifiState() + "</td>");
    			out.write("<td>" + w.getLnt() + "</td>");
    			out.write("<td>" + w.getLat() + "</td>");
    			out.write("<td>" + w.getInstallDate() + "</td>");
    			out.write("</tr>");
    		}
    	}
    	}
    %>
    
    
    </tbody>
    
</table>
<br>
	
</body>
</html>