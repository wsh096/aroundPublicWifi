<%@ page import="bookmark.BookMarkDAO" %>
<%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-22
  Time: 오후 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
</head>
<body>
  <% String BOOKMARK_ID= request.getParameter("ID");
    int result=new BookMarkDAO().deleteBookmark(BOOKMARK_ID);
    System.out.println(result);
  %>
</body>
</html>
<script>
  <%

    if(result==1){

      out.print("alert('성공적으로 삭제 되었습니다.')");
    }else{
      out.print("alert('삭제할수 없습니다.')");
    }
  %>
</script>
<script src="js/toIndex.js"></script>