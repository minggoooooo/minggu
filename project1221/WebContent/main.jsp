<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Cookie[] c = request.getCookies();
	if (c != null) {
		for (Cookie cf : c) {
			if (cf.getName().equals("id")){
				String ids = cf.getValue();
				session.setAttribute("userId", ids);
			}
		}
	}

	// 세션값 가져오기
	String UserId = (String) session.getAttribute("userId"); // Object 타입이므로 다운캐스팅
%>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/layout.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
	<div class="container">
		<%@include file="header.jsp"%>
		<%@include file="background.jsp"%>
		<div class="contents-title">
			<h6>내 주변<br>
			운동 생활<br>
			커뮤니티</h6>
			</div>
		<div class="mid">
			<div class="mid-inner">
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>

</body>
</html>