<%--
  Created by IntelliJ IDEA.
  User: wlals
  Date: 2023-02-20
  Time: 오후 4:45
  To change this template use File | Settings | File Templates.
  to do 북마크 그룹 생성페이지
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <style>
    @import 'css/common.css';
  </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>

<%@ include file="menu.jsp"%>

  <form action="bookmark_group_add_submit.jsp" onsubmit="return checkValue()" id="form" method="get">
    <table>
      <tr><th>북마크 이름</th><td><input type="text" id="BOOKMARK_GROUP_NM" name="BOOKMARK_GROUP_NM"></td></tr>
      <tr><th>순서</th><td><input type="text" id="BOOKMARK_GROUP_SEQ" name="BOOKMARK_GROUP_SEQ"></td></tr>
      <tr><td colspan="2"><button type="submit">추가</button></td></tr>
    </table>
  </form>
</body>
<script>
  function checkValue(){

    if(document.getElementById("BOOKMARK_GROUP_NM").value==="" || document.getElementById("BOOKMARK_GROUP_SEQ").value===""){
      alert("값은 필수입니다.")
      document.getElementById("BOOKMARK_GROUP_NM").focus()
      return false;

    }else if(isNaN(document.getElementById("BOOKMARK_GROUP_SEQ").value)){
      alert("순서 값은 숫자만 가능합니다.")
      return false;
    }
    document.getElementById("form").onsubmit;
  }

</script>
</html>
