<%@ page import="bookmarkgroup.BookMarkGroupDAO" %><%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-21
  Time: 오후 5:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String ID=request.getParameter("ID");
    String BOOKMARK_GROUP_NM=request.getParameter("BOOKMARK_GROUP_NM");
    String BOOKMARK_GROUP_SEQ= request.getParameter("BOOKMARK_GROUP_SEQ");

    BookMarkGroupDAO dao=new BookMarkGroupDAO();
%>
<body>
</body>
</html>
<script>
    <% if (dao.editBookmarkGroup(ID,BOOKMARK_GROUP_NM,BOOKMARK_GROUP_SEQ)!=1){
        out.print("alert(\'이미 존재하는 칼럼이름입니다.(입력값을 확인해주세요);\')");

    }else{
        out.print("alert(\'성공적으로 수정 되었습니다.\');");
    }
    %>

</script>
<script src="js/toIndex.js"></script>