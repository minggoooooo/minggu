<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
<link href="resources/css/crewactivity.css" rel="stylesheet" type="text/css">
<link href="resources/css/longdayCrewgather.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>롱데이 크루 모집</title>
<script src="resources/js/crewactivity.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<%@include file="background.jsp"%>
	<div class="crew-activity">
	<div class="longday_crewgather">
		<div class="longday-header">롱데이 크루 모집</div>
			<div class="longday-body">
				<form action="#" method="post">
					<label for="backgroundPhoto">크루 배경 사진</label> 
					<input type="file" id="backgroundPhoto" name="backgroundPhoto" accept="image/*">
					<label for="crewName">크루 이름</label> 
					<label for="crew-date">일정(required)</label>
					<label for="crew-location">장소</label> 
					<label for="personNum">모집 인원</label> 
					<label for="crew-fee">참가 비용</label> 
					<label for="requirements">가입 조건</label>
					<button type="submit">가입 신청</button>
				</form>
			</div>
	</div>
	</div>
<%@include file="footer.jsp"%>
</body>
</html>
