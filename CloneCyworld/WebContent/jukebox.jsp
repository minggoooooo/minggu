<%@page import="model.jukeboxDAO"%>
<%@page import="model.jukebox"%>
<%@page import="filter.JSFunction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Guestbook"%>
<%@page import="model.GuestbookDAO"%>
<%@page import="model.memberDAO"%>
<%@page import="model.member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/index.css" />	
<link rel="stylesheet" href="./resources/css/jukebox.css" />
<script type="text/javascript" src="./resources/js/jukebox.js"></script>
<%
	String owner_id = request.getParameter("id");
	memberDAO cyMemberDAO = new memberDAO();
	member user = cyMemberDAO.GetMember(owner_id);
	String loginUserId = "";
	if(request.getParameter("id")!=null &&session.getAttribute("loginUserId")!=null && session.getAttribute("loginUserId")!=""){
		loginUserId = session.getAttribute("loginUserId").toString();
	} else {
		JSFunction.alertLocation(response, "로그인하세요", "login.jsp");
	}
	member loginUser = cyMemberDAO.GetMember(loginUserId);
	cyMemberDAO.close();
	jukeboxDAO dao = new jukeboxDAO();
	ArrayList<jukebox> list = dao.getMusicList(owner_id);
	dao.close();
%>
<title>방명록</title>
</head>
<body>
		<div class="container">
			<div class="left-box">
					<div class="box-radius-5 center tool2">Today 222 | Total 1111</div>
					<div class="box-radius-5 page1">
						<div class="skybox">  JUKE BOX </div>
						<div class="dot-line"></div>
						<div class="img-box">
						<img alt="profile-img-${loginUserId}" src="./resources/img/<%=loginUser.getImgName()%>">
						</div>
						<div class="dot-line"></div>
						<div class="select-box">
							<select id="pageSelect" onchange="openPage()">
								<option value="">파도타기</option>
								<option value="https://www.naver.com">네이버</option>
								<option value="https://www.google.com">구글</option>
							</select>
						</div>
					</div>
				</div>
		<div class="right-box">
			<form action="LogoutServlet" method="post">
				<input type="submit" value="로그아웃" style="float: right; margin-right:15px;"/>
			</form>
			<div class="box-radius-5 logo-wrapper">
			<a href="home.jsp?id=<%=owner_id%>">
				<img class="literal-logo" alt="cy-literal-logo"
					src="./resources/img/Cyworld-literal.svg" />
			</a>
			</div>
			<div class="content-box">
				<!-- 주크박스의 기능 넣는 박스  -->
				<c:set var="owner_id" value="<%=owner_id%>"/>
				<c:set var="login_id" value="<%=loginUserId%>"/>
				<c:set var="list" value="<%=list %>"/> 
				<div class="top-box">
				<!-- 버튼 등록 버튼 소유주가 아닐 경우엔 듣기만 가능 -->
					<div class="top-leftbox">
							<c:if test="${list.size()>=1 }">
						<button type="button" onclick="#">듣기</button>
							</c:if>
					</div>
					<div class="top-rightbox">
						<c:if test="${owner_id == login_id}">
							<button type="button" onclick="addMusic()"> 음악 추가 </button>
							<c:if test="${list.size()>=1 }">
							<button type="button" onclick="addList()"> 배경음악 등록 </button>
							</c:if>
						</c:if>
					</div>
				</div>
				<!-- 곡 리스트 및 선택버튼 만들 곳 -->
					<form name="musicfrm" class="musicfrm" id="musicfrm" action="controlbooleanServlet">
				<c:choose>
					<c:when test="${list.size()>=1 }">
				<div class="middle-box-O">
					<input type="hidden" name="owner_id" value="<%=owner_id%>">
					<table class="list-table">
					<tr class="top-tr">
						<td style="width: 30px; text-align: center;"> <input type="checkbox" id="selectAllCheckbox"> </td>
						<td style="width: 40px; text-align: center;"> 번호 </td>
						<td style="width: 480px; padding-left: 10px;"> 곡명 </td>
						<td style="padding-left: 10px"> 아티스트 </td>
					</tr>
					<c:forEach items="${list }" var="i" varStatus="status">
					<tr class="sub-tr">
						<td style="text-align: center;"> <input type="checkbox" name = "checkbox" class="subCheckbox" value="${i.no}"> </td>	
						<td class="indexnum"> ${status.index +1} </td>	
						<td style="padding-left: 10px;"> <img alt="play" style="vertical-align: middle; margin-bottom: 2px;" src="./resources/img/play.png"> ${i.title }</td>	
						<td style="padding-left: 10px;"> ${i.artist }</td>	
					</tr>
					</c:forEach>
					
					</table>	
				</div>
					</c:when>
					<c:otherwise>
				<div class="middle-box-X">
					<input type="hidden" name="owner_id" value="<%=owner_id%>">
						<h2>음악을 추가해주세요</h2>
				</div>
					</c:otherwise>
				</c:choose>	
					</form>
				<div class ="bottom-box">
					<div class="bottom-leftbox">
							<c:if test="${list.size()>=1 }">
						<button type="button" onclick="#">듣기</button>
							</c:if>
					</div>
					<div class="bottom-rightbox">
						<c:if test="${owner_id == login_id}">
							<button type="button" onclick="addMusic()">음악 추가</button>
							<c:if test="${list.size()>=1 }">
							<button type="button" onclick="addList()"> 배경음악 등록 </button>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<div class="menu-box">
	<button type="button" onclick = 'location.href= "./home.jsp?id=<%=owner_id%>"'>  홈 </button>
	<button type="button" onclick = 'location.href= "./photobook.jsp?id=<%=owner_id%>"'> 방명록 </button>
	<button type="button" onclick = 'location.href= "./photobook.jsp?id=<%=owner_id%>"'> 사진첩 </button>
	<button type="button" style="background-color:white; color:black;"> 주크박스 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	</div>
		</div>
</body>
</html>