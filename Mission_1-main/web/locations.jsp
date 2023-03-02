<%@ page import="java.util.ArrayList" %>
<%@ page import="locationhistory.LocationHistoryDTO" %>
<%@ page import="locationhistory.LocationHistoryDAO" %><%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-22
  Time: 오후 3:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<h1>위치 히스토리
</h1>
<body>
  <%@include file="menu.jsp"%>
  <table>
    <tr>
      <th>ID</th>
      <th>X좌표</th>
      <th>Y좌표</th>
      <th>조회일자</th>
      <th>비고</th>
    </tr>
    <% ArrayList<LocationHistoryDTO> list=new LocationHistoryDAO().getHistory();
      for (LocationHistoryDTO dto : list) {
        out.println("<tr>");
        out.println("<td>"+dto.getId()+"</td>");
        out.println("<td>"+dto.getLat()+"</td>");
        out.println("<td>"+dto.getLnt()+"</td>");
        out.println("<td>"+dto.getTime_log()+"</td>");
        out.println("<td><button onclick='location.href=\"./location_delete.jsp?ID="+dto.getId()+"\"'>삭제</button></td>");
        out.println("</tr>");
      }
            ;%>
  </table>
</body>
</html>
