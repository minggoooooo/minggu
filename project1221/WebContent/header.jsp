<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="resources/css/layout.css" rel="stylesheet" type="text/css">
<link href="resources/css/header.css" rel="stylesheet" type="text/css">
<header class="top">
	<div class="top-inner">
		<div class="top-left">
			<a href="./main.jsp" class="top-logo"></a>
		</div>
		<div class="top-center">
			<div class="header">
				<div class="dropdown">
					<button class="dropbtn" id="1">사이트 소개</button>
				</div>
				<div class="dropdown">
					<button class="dropbtn" id="2">코스 찾기</button>
				</div>
				<div class="dropdown">
					<button class="dropbtn">크루 활동</button>
					<div class="dropdown-content">
						<a href="CrewBoardList-OnedayActivity.jsp">일일크루</a> <a
							href="CrewBoardList-LongActivity.jsp">장기크루</a>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn">크루 모집</button>
					<div class="dropdown-content">
						<a href="CrewBoardList-OnedayGather.jsp">일일크루</a> <a
							href="CrewBoardList-LongGather.jsp">장기크루</a>
					</div>
				</div>
				<div class="dropdown">
					<button class="dropbtn" id="3">마이 페이지</button>
				</div>
			</div>
		</div>
		<!-- 1219 변경 부분 -->
		<div class="top-right">
			<div class="top-login">
				<%
				String memId = (String) session.getAttribute("userId");
				if (memId != null) {
				%>
				<a class="logout" href="LogoutServlet"
					style="color: black; text-decoration: none;">로그아웃</a>
			</div>
			<%
			} else {
			%>
			<a class="login" href="login.jsp">로그인</a>
			<%
			}
			%>
			<div class="top-signup">
				<a class="signup" href="signUp.jsp">회원가입</a>
			</div>
		</div>
	</div>

</header>
<script src="resources/js/header.js"></script>
