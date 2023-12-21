<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>아이디 중복검사</title>
</head>
<body>
	<form action="IdCheckServlet" method="get" name="idCheckForm">
		<label>아이디</label>
		<input type="text" name="id" value="${userId}"/>
		<button type="submit">중복체크</button><br>
		
		<c:if test="${result == 1}">
			<p>${userId}는 이미 사용중인 아이디입니다</p>
		</c:if>
		<c:if test="${result == 0}">
			<p>${userId}는 사용 가능한 아이디입니다</p>
			<button type="button" onclick="idUseOk()">사용</button>
		</c:if>
	</form>
	
<script type="text/javascript" src="./resources/js/register.js"></script>
</body>
</html>