<%@page import="model.jukebox"%>
<%@page import="model.jukeboxDAO"%>
<%@page import="model.photobook"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.photobookDAO"%>
<%@page import="model.GuestbookDAO"%>
<%@page import="filter.JSFunction"%>
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
<link rel="stylesheet" href="./resources/css/home.css" />
<script src="https://www.youtube.com/iframe_api"></script>
<script type="text/javascript" src="./resources/js/home.js"></script>
<%
	memberDAO cyMemberDAO = new memberDAO();
	String id = request.getParameter("id");
	member user = cyMemberDAO.GetMember(id);
	if(session.getAttribute("loginUserId")!=null && session.getAttribute("loginUserId")!=""){
		String loginUserId = session.getAttribute("loginUserId").toString();
	}
	if (id ==null){
		JSFunction.alertLocation(response, "로그인하세요", "login.jsp");
	}
	cyMemberDAO.close();
	GuestbookDAO dao = new GuestbookDAO();
	int update = dao.todaycount(id);
	dao.close();
	photobookDAO dao2 = new photobookDAO();
	ArrayList<photobook> photobook = dao2.readlist(id);
	int update2 = dao2.todaycount(id);
	dao2.close();
	jukeboxDAO dao3 = new jukeboxDAO();
	ArrayList<jukebox> playlist = dao3.getPlayList(id);
	String[] list = new String[playlist.size()];
	for(int i=0; i<playlist.size(); i++){
		list[i] = playlist.get(i).getId();
	}
    dao3.close();
%>
<title><%=user.getId()%>님의 미니홈피</title>
</head>
<script>
        <%= list %>
        setPlaylist(list);
    </script>

<body>
	<div class="container">
		<div class="left-box">
			<div class="box-radius-5 center tool1"><%=user.getId()%>님의 미니홈피</div>
			<div class="box-radius-5 center tool2">Today 222 | Total 1111</div>
			<div class="box-radius-5 center-layout-column page1">
				<img class="profile" alt="profile"
					src="./resources/img/<%=user.getImgName()%>" />
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
		<div class="right-box page2">
			<form action="LogoutServlet" method="post">
				<input type="submit" value="로그아웃" style="float: right;"/>
			</form>
			<div class="box-radius-5 logo-wrapper">
				<img class="literal-logo" alt="cy-literal-logo"
					src="./resources/img/Cyworld-literal.svg" />
			</div>
			<div class="box-radius-5 center-layout-column page3">
				<div class="bgm-wrapper">
					<div id="player">
					</div>
					<img alt="player" src="./resources/img/player.png" class="player">
					<a onclick="playVideo()" class="play"></a>
					<a onclick="stopVideo()" class="stop"></a>
					<a onclick="toggleVideoTitle()" class="playlist"></a>
					<div id="videoTitleContainer">
						<c:forEach items="<%=playlist %>" var="i">
							${i.title }
						</c:forEach>
					</div>
				</div>
				<div class="right-box-content">
					<div>
						<p>일기</p>
						<c:choose>
						<c:when test="<%=photobook.size() ==0 %>">
						<p>사진</p>
						</c:when>
						<c:otherwise>
						<p><%=photobook.get(0).getTitle() %></p>
						</c:otherwise>
						</c:choose>
					</div>
					<div style="border: 1px solid black">
						<div class="menu-wrapper" style="display: flex">
							<div class="menu">투데이</div>
							<div class="menu">주크박스</div>
						</div>
						<div class="menu-wrapper" style="display: flex">
							<a class="menu" href="./photobook.jsp?id=<%=user.getId()%>">사진첩
								<c:set var="update2" value="<%=update2 %>"/>
								<c:if test="${update2 >= 1 }">
									<span class="update_literal"> &nbsp; N </span>
								</c:if>
							</a>
							<a class="menu" href="./guestbook.jsp?id=<%=user.getId()%>">
								방명록 
								<c:set var="update" value="<%=update %>"/>
								<c:if test="${update >= 1 }">
									<span class="update_literal"> &nbsp; N </span>
								</c:if>
							</a>
						</div>
					</div>
				</div>
				<img class="big-img" alt="big"
					src="./resources/img/2023-11-07 093057.png"/>
			</div>
		</div>
	<div class="menu-box">
	<button type="button" style="background-color:white; color:black;">  홈 </button>
	<button type="button" onclick = 'location.href= "./guestbook.jsp?id=<%=user.getId()%>"'> 방명록 </button>
	<button type="button" onclick = 'location.href= "./photobook.jsp?id=<%=user.getId()%>"'> 사진첩 </button>
	<button type="button" onclick ='location.href= "./jukebox.jsp?id=<%=user.getId()%>"'> 주크박스 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	</div>
	</div>
</body>
</html>