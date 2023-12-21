<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String owner_id = request.getParameter("owner_id");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/index.css" />
<script type="text/javascript" src="./resources/js/jukebox.js"></script>
</head>
<body>
<h2>음악 추가</h2>
	<form action="addMusicServlet" name="idCheckForm" id="musicForm">
		<input type="hidden" name="owner_id" value="<%=owner_id%>"/>
		<input type="text" name="title" placeholder="곡 제목" required> <Br/>
		<input type="text" name="artist" placeholder="아티스트" required> <br/>
		<input type="text" name="id" placeholder="유투브id" required> <br/>
		<button type="button" onclick="musicOk()">
			음악 추가
		</button>
	</form>
	
</body>
</html>