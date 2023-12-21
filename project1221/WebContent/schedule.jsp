<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/schedule.css" rel="stylesheet" type="text/css">
<script src="resources/js/crewactivity.js"></script>
<meta charset="UTF-8">
<title>album</title>
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
	<div class="schedule-container">
  <div class="schedule-item">
            <div class="schedule-date">날짜</div>
            <div class="schedule-participants">참석 인원</div>
            <div class="schedule-status">모집중/마감</div>
            <div class="schedule-fee">참가비</div>
        </div>
  <div class="schedule-item">
            <div class="schedule-date">날짜</div>
            <div class="schedule-participants">참석 인원</div>
            <div class="schedule-status">모집중/마감</div>
            <div class="schedule-fee">참가비</div>
        </div>
  <div class="schedule-item">
            <div class="schedule-date">날짜</div>
            <div class="schedule-participants">참석 인원</div>
            <div class="schedule-status">모집중/마감</div>
            <div class="schedule-fee">참가비</div>
        </div>
  <div class="schedule-item">
            <div class="schedule-date">날짜</div>
            <div class="schedule-participants">참석 인원</div>
            <div class="schedule-status">모집중/마감</div>
            <div class="schedule-fee">참가비</div>
        </div>
  <div class="schedule-item">
            <div class="schedule-date">날짜</div>
            <div class="schedule-participants">참석 인원</div>
            <div class="schedule-status">모집중/마감</div>
            <div class="schedule-fee">참가비</div>
        </div>
  <div class="schedule-item">
            <div class="schedule-date">날짜</div>
            <div class="schedule-participants">참석 인원</div>
            <div class="schedule-status">모집중/마감</div>
            <div class="schedule-fee">참가비</div>
        </div>
        	<div id="pagination1">
        </div>
  			<div id="pagination2">
				<a href="#" class="prev">이전</a> 
				<a href="#" class="page active">1</a>
				<a href="#" class="page">2</a> 
				<a href="#" class="page">3</a> 
				<a href="#" class="next">다음</a>
			</div>      
			<div id="pagination3">
				<button class="btn-write" onclick="location.href='scheduleregister.jsp'">일정 등록</button>
			</div>	
</div>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>