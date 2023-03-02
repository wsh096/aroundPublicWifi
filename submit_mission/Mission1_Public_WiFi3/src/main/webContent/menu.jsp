<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
		<a href = "<%= request.getContextPath() %>/index.jsp">홈</a> | 
	
		<a href = "<%= request.getContextPath() %>/history.jsp">위치 히스토리 목록</a> | 
	
		<a href = "<%= request.getContextPath() %>/openApiInfoGet.jsp">Open API 와이파이 정보 가져오기</a> |
		
		<!-- <a href = "<%= request.getContextPath() %>/bookmark.jsp">즐겨 찾기 보기</a> |
		
		<a href = "<%= request.getContextPath() %>/bookmarkadmin.jsp">즐겨 찾기 그룹 관리</a>  -->
		<br>
</body>
</html>