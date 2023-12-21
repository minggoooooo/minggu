<%@page import="model.CrewScheduleMemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.CrewRecruitDAO"%>
<%@page import="model.CrewRecruitDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<%@include file="background.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="loginCheck.jsp" %>
<%
	String crewName = request.getParameter("crewName");
	CrewRecruitDTO crew = new CrewRecruitDAO().selectRecruitDetail(crewName);
	String UserId = (String)session.getAttribute("userId");	// 로그인된 아이디
	String adminId = crew.getAdminId();
	pageContext.setAttribute("adminId", adminId);			// 크루방장 아이디
	CrewRecruitDAO dao = new CrewRecruitDAO();				// 특정 단기크루의 모든 memId 가져오기
	List<CrewScheduleMemberDTO> memberList = dao.selectMemid(crewName);
	
	boolean boo = true;
	if(adminId !=null){
		for(CrewScheduleMemberDTO mem : memberList) {
			if(mem.getMemId().equals(UserId)) {
				boo = false;
			}
		}
		if(adminId.equals(UserId)) {
			boo = false;
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단기크루 모집글 상세보기</title>
<link href="./resources/css/crewactivity.css" rel="stylesheet" type="text/css">
</head>
<body>
	

<div class="crew-activity">
	<div class="activity-header">
		<div class="crew-sort">
			<input type="button" class="sort" id="home" value="홈"> 
			<input type="button" class="sort" id="board" value="게시판"> 
			<input type="button" class="sort" id="date" value="일정"> 
			<input type="button" class="sort" id="photobook" value="앨범">
		</div>
		<div class="activity-background">
			<img src="./resources/images/back.jpg" alt="Background Image"
				width="300" height="200">
		</div>
	</div>
	
	<form action="./joinCrew.crew" method="post">
	<div class="activity-details">
		<c:if test=""></c:if>
		<p>크루명 : <%=crew.getCrewName() %></p>
		<p>일정 : <%=crew.getGatherDate() %></p>
		<p>장소 : <%=crew.getLocation()%></p>
		<p>모집 인원 : <%=crew.getMemberNum() %></p>
		<p>가입 조건 : <%=crew.getContent() %></p>
		<p>방장 ID : <%=crew.getAdminId() %></p>
		
		<input type="hidden" name="no" value="<%=crew.getNo()%>">
		<input type="hidden" name="crewName" value="<%=crew.getCrewName()%>">
		<input type="hidden" name="memberNum" value="<%=crew.getMemberNum()%>">
		<input type="hidden" name="adminId" value="<%=crew.getAdminId()%>">
		<input type="hidden" name="userId" value="<%=UserId%>">
		
		<!-- 접속자와 크루 방장이 같으면 크루참여 버튼 안보임 -->
				
		<c:if test="<%=boo%>">
			<button type="submit">크루참여</button>
		</c:if>
	</div>
	</form>
	
</div>

<%@include file="footer.jsp"%>