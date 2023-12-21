<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="loginCheck.jsp" %>
<%
	String crewName = request.getParameter("crewName");

%>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/tablewrite.css" rel="stylesheet"
	type="text/css">
<meta charset="UTF-8">
<title>크루 게시판 글쓰기 페이지</title>
</head>
<body>
<body>
	<%@include file="header.jsp"%>
	<%@include file="background.jsp"%>
	<div class="postregister">
			<div class="post-header">글쓰기 등록</div>
			<div class="post-body">
				<form action="./crewBoard.board" name="frm" method="post"  onsubmit="return categoryCheck()" enctype="multipart/form-data">
						
						<select name="category">
							<option id="choice">게시판을 선택해주세요</option>
							<option value="notice">공지사항</option>
							<option value="board">자유게시판</option>
							<option value="greeting">가입인사</option>
						</select><br>
						
						<input type="hidden" name="crewName" value="<%=crewName%>"/>
						
						<input type="text" id="post-title" name="post-title" placeholder="제목을 입력하세요" required >
						<input type="text" id="contents" name="contents" placeholder="내용" required > 
						<label for="imagePhoto" class="custom-file-upload">
 							<img src="upload-icon.png"> 이미지 업로드
						</label>
						<input type="file" id="imagePhoto" name="imagePhoto">
						<input type="hidden" id="date" name="날짜" > 
						<input type="hidden" id="count" name="조회수"> 
						<button type="submit">작성 완료</button>
				</form>
			</div>
		</div>
	<%@include file="footer.jsp"%>
</body>
</html>
					
					