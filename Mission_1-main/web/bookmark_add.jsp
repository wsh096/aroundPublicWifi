<%@ page import="bookmark.BookMarkDAO" %><%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-22
  Time: 오전 1:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<%
    String mgrNo=request.getParameter("mgrNo");
    String BOOKMARK_GROUP_NM=request.getParameter("bookmark_group_nm");

    BookMarkDAO dao=new BookMarkDAO();
%>
</body>
</html>
<script>
    <%if ( (BOOKMARK_GROUP_NM!="")&&(1==dao.insertBookmark(mgrNo,BOOKMARK_GROUP_NM))){
        out.print("alert('성공적으로 추가되었습니다.')");
    }else{
        out.print("alert('오류가 발생하였습니다.')");
    }%>

</script>
<script src="js/toIndex.js"></script>