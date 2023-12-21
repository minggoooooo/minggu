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
<link rel="stylesheet" href="./resources/css/guestbook.css" />
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
	GuestbookDAO guestbookDAO = new GuestbookDAO();
	ArrayList<Guestbook> list = guestbookDAO.getList(owner_id);
	cyMemberDAO.close();
	guestbookDAO.close();
%>
<title>방명록</title>
</head>
<body>
		<div class="container">
			<div class="left-box">
					<div class="box-radius-5 center tool1"><%=user.getId()%>님의 미니홈피</div>
					<div class="box-radius-5 center tool2">Today 222 | Total 1111</div>
					<div class="box-radius-5 center-layout-column page1">
						<div class="profile-box">
						<img class="profile" alt="profile"
							src="./resources/img/<%=user.getImgName()%>" />
						</div>
						<div class="dot-line"></div>
						<div>					<div class="box-radius-5">화이팅문구</div>
							<div><%=user.getId()%>님의미니홈피에오신것을환영합니다!</div>
						</div>
						<div class="dot-line"></div>
						<div>
							<div>소개글</div>
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
				<c:set var="owner_id" value="<%=owner_id%>"/>
				<c:if test="${loginUserId != owner_id}">
			<div class="writer-box">
				<div class="user-info">
					${loginUserId }
				</div>
				<form action="GuestbookServlet" method="post" class="writer-form">
					<input type="hidden" name="owner_id" value=<%=owner_id%> />
					<div class="writer-form-box">
					<div>
						<img alt="profile-img-${loginUserId}" src="./resources/img/<%=loginUser.getImgName()%>">
					</div>
					<div>
						<textarea name="content"></textarea>
					</div>
					</div>
					<div style="height: 100px;">
					<button type="submit">등록</button>
					</div>
				</form>
			</div>
				</c:if>
				<c:set var="list" value="<%=list%>"/>
				<c:forEach var="i" items="${list }" varStatus="loop">
				<c:set var="counter" value="${list.size() - loop.index}"/>
				<div class="center-layout-column box">
					<div class="top-box">
						<p> &nbsp; no.${counter} &nbsp; ${i.id }  &nbsp; <a href="home.jsp?id=${i.id }"> <img alt="home" src="./resources/img/image-removebg-preview.png">  </a>  &nbsp;  (${i.created })</p> 
					<c:if test ="${i.id == loginUserId || owner_id == loginUserId}">
					<div class="delete_button">
						<button type ="button" onclick='location.href="deleteGuestbookServelt?no=${i.no}&id=${owner_id}"'> 삭제하기 </button>
					</div>
					 </c:if>
					</div>
					<div class="middle-box">
						<div class="img-box">
							<img alt="profile-img-${i.id}" src="./resources/img/${i.imgFile}">
						</div>
						<div class="content-box2">
						<br/>
							${i.content}
						</div>
					</div>
					<div class="bottom-box">
						<div>
						<c:set var="repList" value="${i.replyList}"/>
						<c:forEach var="k" items="${repList}">
						<div class="reply-box">
						<p> ${k.id } (${k.created }) ${k.content } </p>
						<c:if test ="${k.id == loginUserId || owner_id == loginUserId}">
							<button type ="button" onclick='location.href="deleteGuestbookReplyServelt?r_no=${k.r_no}&id=${owner_id}"'> 삭제하기 </button>
						 </c:if>
						 </div>
						</c:forEach>
						</div>
						<div class="input-container">
							<div class="id-box">
							<%=loginUserId %>
							</div>
							<div class="form-box">
							<form action="GuestbookReplyServlet" class="reply-form">
							<input type="hidden" name="b_no" value="${i.no}"/>
							<input type="hidden" name="id" value="<%=loginUserId%>"/>
							<input type="hidden" name="owner_id" value="<%=owner_id%>"/>
							<input type="text" name="content2"/>
							<button type="submit">등록</button>
							</form>
							</div>
						</div>
					</div>
			</div>
				</c:forEach>
		</div>
		</div>
		<div class="menu-box">
	<button type="button" onclick = 'location.href= "./home.jsp?id=<%=owner_id%>"'>  홈 </button>
	<button type="button" style="background-color:white; color:black;"> 방명록 </button>
	<button type="button" onclick = 'location.href= "./photobook.jsp?id=<%=owner_id%>"'> 사진첩 </button>
	<button type="button" onclick ='location.href= "./jukebox.jsp?id=<%=owner_id%>"'> 주크박스 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	</div>
		</div>
</body>
</html>