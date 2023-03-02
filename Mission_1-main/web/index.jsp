<%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-13
  Time: 오전 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>와이파이 정보 구하기</title>
    <style>
      @import url("css/common.css");
    </style>
  </head>
  <body>
    <h1>와이파이 정보 구하기</h1>


    <%@ include file="menu.jsp"%>

    <form id="searchingForm" onsubmit="return searchingWifi()" action="./index.jsp" method="get">
      LAT: <input id="LAT" name="LAT"> , LNT: <input id="LNT" name="LNT"> <button type="button" onclick="getPosition()">내 위치 가져오기</button> <button>근처 WIPI 정보 보기</button>
    </form>

    <%@ include file="index_table.jsp"%>

  </body>
</html>

<script src="js/common.js">
</script>