<%@page import="model.PhotoDAO"%>
<%@page import="model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String crewName = request.getParameter("crewName");
	List<BoardDTO> photoList = new PhotoDAO().selectPhotosByCrew(crewName);
%>
<!DOCTYPE html>
<html>
<head>
<link href="./resources/css/album.css" rel="stylesheet" type="text/css">
<script src="./resources/js/crewactivity.js"></script>
<meta charset="UTF-8">
<title>앨범</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="background.jsp"%>

	<div class="crew-table">
		<div class="table-header">
			<div class="crew-sort">
				<input type="button" class="sort" id="home" value="홈"> 
				<input type="button" class="sort" id="board" value="게시판"> 
					<input type="button" class="sort" id="date" value="일정"> 
					<input type="button" class="sort" id="photobook" value="앨범">
			</div>
		</div>
		<div class="album-container">
			<c:forEach items="<%=photoList%>" var="photo">
			<c:if test="${not empty photo.imgName}">
				<div class="album-item">
				<a href="photoPage.jsp?imgName=${photo.imgName}">
				<img src="./resources/images/${photo.imgName}"></a>
				<a href="DeletePhotoServlet?no=${photo.no}">삭제</a>
				</div>
			</c:if> 
			</c:forEach>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>