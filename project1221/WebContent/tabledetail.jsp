<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/tabledetail.css" rel="stylesheet"
	type="text/css">
<meta charset="UTF-8">
<title>크루 게시판 글 상세보기 페이지</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="background.jsp"%>
	<div class="container-detail">
		<h1>게시글 상세보기</h1>
		<hr id="line1">
		<div class="post">
			<img src="사진경로" alt="업로드된 사진">
		</div>
		<hr id="line2">
		<div class="writing">
		<div class="title">제목</div>
		<div class="contents">내용</div>
		<div class="comment">
            <input type="text" placeholder="댓글을 작성해주세요."/>
            <button>댓글 작성</button>
        </div>
		<div class="buttons">
			<a href="게시글_수정_페이지_주소">게시글 수정</a> 
			<a href="게시글_삭제_처리_서블릿_주소">게시글 삭제</a>
		</div>
	</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>