<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title></title>

<link href="./resources/css/layout.css" rel="stylesheet">
<link href="./resources/css/finder-RS.css" rel="stylesheet">
</head>
<body>

	<!-- login.jsp 구조 -->
	<div id="root">
		<div class="app">
			<div class="container">
				<!-- header.jsp 시작 -->
				<!-- <div class="banner"></div>
				 <div class="header"></div> -->
				<%@ include file="header.jsp"%>
				<!-- header.jsp 끝 -->

				<div class="mid">
					<div class="mid-inner">
						<div class="login_wrap">
							<div class="login_container">
								<div class="login_cont">
									<!-- 로고 시작 -->
									<div class="logo">
										<a href="/index.html"> <img
											src="./resources/images/img_logo.png" alt="로고" width="100"
											height="100">
										</a>
									</div>
									<!-- 로고 끝 -->
									<h1>비밀번호 찾기</h1>
									<ul class="frm_list">
										<li class="id">
											<span>ID : <span>${id}</span></span>
										</li>
										<br>
										<li class="pw">
											<span>PW : <span>${pw}</span></span>
										</li>
									</ul>
									<div class="btn_wrap">
										<a href="login3.jsp" class="btn normal login type01">로그인</a>
									</div>
									


									<!-- 배너 시작 -->
									<div class="login_banner">
										<div class="page-move">
											<a href="main.jsp">메인으로 ></a>
										</div>
									</div>
									<!-- 배너 끝 -->
								</div>
							</div>

							<script type="text/javascript">
								//enter키 기능
								function CheckKey(e) {
									var e = e || window.event;
									key = e.keyCode;
									if (key == 13) {
										send();
									}
								}
							
							</script>
							
						</div>
					</div>
				</div>



				<!-- footer.jsp 시작 -->
				<!--  <div class="footer"></div> -->
				<!-- footer.jsp 끝 -->
				<%@ include file="footer.jsp"%>
			</div>
		</div>
	</div>

</body>

</html>