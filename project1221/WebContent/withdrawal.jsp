<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="loginCheck.jsp" %>

<%
	MemberDTO dto = (MemberDTO)session.getAttribute("memberDTO");
	String id = dto.getId();
	String pw = dto.getPassword();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link href="resources/css/layout.css" rel="stylesheet">
<link href="resources/css/withdrawal.css" rel="stylesheet">
</head>
<body>

	<div id="root">
		<div class="app">
			<div class="container">
				<!-- header.jsp 시작 -->
				<%@ include file="header.jsp"%>
				<!-- header.jsp 끝 -->

				<div id="mm_body" class="mm_page">
					<!-- 본문 감싸기 -->
					<div class="mm_page-content">

						<!-- 본문 영역 -->
						<div id="content">
							<div id="reconfirmPasswd">

								<div class="reconfirmTop">
									<h2>회원 탈퇴</h2>
									<div class="reconfim_i" ></div>
									<p class="txt">
										회원님의 소중한 개인정보를 안전하게 보호하고<br>개인정보 도용으로 인한 피해를
										예방하기 위하여 비밀번호를 확인합니다.<br>비밀번호는 타인에게 노출되지 않도록 주의해주세요.
									</p>
								</div>

								<form action="DeleteMemberServlet" name="form_pw" id="form_pw" method="post">
									
									<fieldset>
										
										<div class="frm">
											<ul>
												<li><span class="lab">아이디</span> <strong><%=id %></strong></li>
												<li><span class="lab">비밀번호</span> <label>
												<span style="display: none;">ENTER PASSWORD</span> 
												<input type="password" id="user_password" name="pwCheck" class="txt-input"></label>
												<input type="hidden" name="id" value="<%=id%>">
												<input type="hidden" name="pw" value="<%=pw%>"></li>
											</ul>
										</div>
										<!-- .frm -->
										<div class="btns">
											<button type="submit" id="CSSbuttonBlack">확인</button>
											<a href="/index.html" class="CSSbutton">취소</a>
										</div>
										<!-- .btns -->
									</fieldset>
								</form>
							</div>
							<!-- #reconfirmPasswd -->
						</div>
						<!-- #content -->
					</div>
				</div>
				<%@ include file="footer.jsp"%>
			</div>
		</div>
	</div>



</body>
</html>