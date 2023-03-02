<%@ page import="locationhistory.LocationHistoryDAO" %><%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-22
  Time: 오후 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%
  String ID= request.getParameter("ID");


%>
</body>
</html>
<script>
  <%
   if(new LocationHistoryDAO().deleteHistory(ID)==1){
    out.print("alert('삭제 되었습니다')");
  }else{
     out.print("alert('오류가 발생했습니다.')");
  }
  %>
</script>
<script src="js/toIndex.js"></script>
