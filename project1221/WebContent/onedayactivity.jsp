<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/crewactivity.css" rel="stylesheet" type="text/css">
<link href="resources/css/onedayCrewgather.css" rel="stylesheet" type="text/css">
<script src="resources/js/crewactivity.js"></script>
<meta charset="UTF-8">
<title>원데이 크루 활동</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="background.jsp"%>
	<div class="crew-activity">
		<div class="activity-header">
	<div class="table-header">
		<div class="crew-sort">
			<input type="button" class="sort" id="home" value="홈"> 
			<input type="button" class="sort" id="photobook" value="앨범">
		</div>
	</div>
	<div class="oneday_crewgather">
		<div class="oneday-header">원데이 크루 활동</div>
			<div class="oneday-body">
				<form action="#" method="post">
					<label for="backgroundPhoto">크루 배경 사진</label> 
					<input type="file" id="backgroundPhoto" name="backgroundPhoto" accept="image/*">
					<label for="crewName">크루 이름</label> 
					<label for="crew-date">일정(required)</label>
					<label for="crew-location">장소</label> 
					<label for="personNum">모집 인원</label> 
					<label for="crew-fee">참가 비용</label> 
					<label for="requirements">가입 조건</label>
				</form>
			</div>
	</div>
</div>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>