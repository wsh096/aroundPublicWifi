<%@ page import="wifiinfo.WifiInfoDAO" %>
<%@ page import="util.OpenApiUtil" %><%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-13
  Time: 오후 1:29
  To change this template use File | Settings | File Templates.

  Todo Open api 와이파이 가져오기 실행  및 화면에  x개의 WIFI 정보를 정상적으로 저장하였습니다.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
    </head>
<body>
<%
    if(WifiInfoDAO.TABLECOUNT!= OpenApiUtil.TOTALAMOUNT){
        WifiInfoDAO dao = new WifiInfoDAO();
        dao.getDataFromApi();
        out.println("데이터 를 업데이트 했습니다." );

    }else {
        out.println("데이터가 최신 상태입니다.");
        out.println("데이터 수:" + WifiInfoDAO.TABLECOUNT);
    }
    out.println("<h1> "+WifiInfoDAO.TABLECOUNT+" 개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>");
    out.println("<a href=\"./index.jsp\">홈 으로 가기</a>");
%>
</body>
</html>
