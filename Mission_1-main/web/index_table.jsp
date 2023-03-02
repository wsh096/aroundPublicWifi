<%@ page import="wifiinfo.WifiInfoDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="wifiinfo.WifiInfoDTO" %><%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-17
  Time: 오후 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<html>
<head>
    <style>
        @import url("css/common.css");
    </style>

</head>
<body>
<table>
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
    <%
        String lat=request.getParameter("LAT");
        String lnt=request.getParameter("LNT");

        if(lat!=null && lnt!=null){ //피라미터 값이 있을때
            WifiInfoDAO dao=new WifiInfoDAO();

            ArrayList<WifiInfoDTO> list=dao.getWifiInfo(lat,lnt);
            if (list.size()!=0) {
                for (WifiInfoDTO dto : list) {
                    out.println("<tr>");
                    out.println("<td>" + dto.getDistance() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_MGR_NO() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_WRDOFC() + "</td>");
                    out.println("<td><a href=\"wifi_info_table.jsp?mgrNo=" + dto.getX_SWIFI_MGR_NO() + "&distance=" + dto.getDistance() + "\">" + dto.getX_SWIFI_MAIN_NM() + "</a></td>");
                    out.println("<td>" + dto.getX_SWIFI_ADRES1() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_ADRES2() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_INSTL_FLOOR() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_INSTL_MBY() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_INSTL_TY() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_SVC_SE() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_CMCWR() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_CNSTC_YEAR() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_INOUT_DOOR() + "</td>");
                    out.println("<td>" + dto.getX_SWIFI_REMARS3() + "</td>");
                    out.println("<td>" + dto.getLNT() + "</td>");
                    out.println("<td>" + dto.getLAT() + "</td>");
                    out.println("<td>" + dto.getWORK_DTTM() + "</td>");
                    out.println("</tr>");
                }
            }else{
                out.print("<tr><td class='nodata' colspan='17'>데이터가 존재하지 않습니다.</td></tr>");
            }
    }else{
            out.print("<tr><td class='nodata' colspan='17'>위치정보를 입력한 후에 조회해 주세요</td></tr>");
        }
    %>

</table>

</body>
</html>
