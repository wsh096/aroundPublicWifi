<%@ page import="bookmarkgroup.BookMarkGroupDAO" %><%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-21
  Time: 오후 5:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String ID=request.getParameter("ID");
        BookMarkGroupDAO dao=new BookMarkGroupDAO();
        int result=dao.deleteBookmarkGroup(ID);
    %>
</head>
<body>
</body>
</html>
<script>
    <% if (result!=1){
        out.print("alert(\' 오류가 발생하였습니다.\')");
    }else{
        out.print("alert(\'성공적으로 삭제되었습니다.\')");
    }
    %>

</script>

<script src="js/toIndex.js"></script>