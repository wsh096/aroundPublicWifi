<%@ page import="java.util.ArrayList" %>
<%@ page import="bookmarkgroup.BookMarkGroupDTO" %>
<%@ page import="bookmarkgroup.BookMarkGroupDAO" %><%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-21
  Time: 오후 3:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%
  ArrayList<BookMarkGroupDTO> list=new BookMarkGroupDAO().getBookmarkGroupInfo();
%>

</head>
<body>
  <table>
    <tr>
      <th>ID</th>
      <th>북마크 이름</th>
      <th>순서</th>
      <th>등록일자</th>
      <th>수정일자</th>
      <th>비고</th>
    </tr>
    <%if(list.size()!=0) {
      for (BookMarkGroupDTO dto : list) {
        out.println("<tr>");
        out.println("<td>" + dto.getBOOKMARK_GROUP_ID() + "</td>");
        out.println("<td>" + dto.getBOOKMARK_GROUP_NM() + "</td>");
        out.println("<td>" + dto.getBOOKMARK_GROUP_SEQ() + "</td>");
        out.println("<td>" + dto.getBOOKMARK_GROUP_CD() + "</td>");
        out.println("<td>" + dto.getBOOKMARK_GROUP_ED() + "</td>");
        out.println("<td><a href='bookmark_group_edit.jsp?ID=" + dto.getBOOKMARK_GROUP_ID() + "'>수정</a> <a href='bookmark_group_delete.jsp?ID=" + dto.getBOOKMARK_GROUP_ID() + "'>삭제</a></td>");
        out.println("</tr>");
      }
    }else{
      out.print("<tr><td class='nodata' colspan='6'>정보가 존재하지 않습니다.</td></tr>");
    }
    %>

  </table>
</body>
</html>
