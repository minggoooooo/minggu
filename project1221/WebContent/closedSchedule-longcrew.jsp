<%@page import="model.CrewRecruitDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.CrewRecruitDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String crewName = request.getParameter("crewName");
	CrewRecruitDAO dao = new CrewRecruitDAO();
	List<CrewRecruitDTO> scheduleList = dao.selectClosedSchedule2(crewName);
	
%>
<!DOCTYPE html>
<html>
<head>
<link href="./resources/css/schedule2.css" rel="stylesheet" type="text/css">
<script src="./resources/js/crewactivity.js"></script>
<meta charset="UTF-8">
<title>마감된 일정 페이지(장기크루)</title>
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
			<div class="schedule-option">
				<select>
					<option value="scheduled" selected>예정된 일정</option>
					<option value="closed">마감된 일정</option>
				</select>
			</div>
			</div>
		<div class="schedule-table">
			<div class="schedule-container">
			
				<c:forEach items="<%=scheduleList%>" var="schedule">
					<div class="schedule-item">
			            <div class="schedule-date">일정 제목 : ${schedule.title}</div>
			            <div class="schedule-participants">모임일자 : ${schedule.gatherDate}</div>
			            <div class="schedule-status">모임장소: ${schedule.location}</div>
			            <div class="schedule-fee">${schedule.content}</div>
		        	</div>
			  	</c:forEach>
				
			</div>
        	<div id="pagination">
	  			<div id="pagination1">
					<a href="#" class="prev">이전</a> 
					<a href="#" class="page active">1</a>
					<a href="#" class="page">2</a> 
					<a href="#" class="page">3</a> 
					<a href="#" class="next">다음</a>
				</div>      
	</div>
	</div>
	</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>