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
<link rel="stylesheet" href="./resources/css/photobookupdate.css" />
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
%>
<title>방명록</title>
</head>
<body>
<script>
		function setThumbnail(event) {
			var reader = new FileReader();
			var file = event.target.files[0];

			if (file && file.type.match("image.*")) {
				reader.onload = function(e) {
					var img = document.createElement("img");
					img.setAttribute("src", e.target.result);
					document.querySelector("#photo-container").innerHTML = "";
					document.querySelector("#photo-container").appendChild(img);
				}

				reader.readAsDataURL(file);
			}
		}
	</script>
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
					src="./resources/img/Cyworld-literal.svg"/>
			</a>
			</div>
			<div class="content-box">
				<div class="main-box">
					<form action="photobookServlet" method="post" enctype="multipart/form-data" class="photobookform">
						<input type="hidden" name="id" value="<%=owner_id%>">
						<input type="text" name="title" placeholder="제목" required style="margin-bottom: 5px; height: 25px;">
						<input type="file" name="photo" onchange="setThumbnail(event)" style="margin-bottom: 10px;" />
						<div id="photo-container"></div>
						<textarea cols="20" name="content" placeholder="내용 입력" style="margin-bottom: 10px;  resize: none;"></textarea>
						<input type="submit" value="작성하기" class="submitbt">
					</form>
				</div>
			</div>
			
		</div>
	<div class="menu-box">
	<button type="button" onclick ='location.href= "./home.jsp?id=<%=owner_id%>"'>  홈 </button>
	<button type="button" onclick ='location.href= "./guestbook.jsp?id=<%=owner_id%>"'> 방명록 </button>
	<button type="button"  style="background-color:white; color:black;"> 사진첩 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	<button type="button"> 공백 </button>
	</div>
		</div>
</body>
</html>