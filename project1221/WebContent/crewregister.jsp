<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="resources/css/crewregister.css" rel="stylesheet"
	type="text/css">
<%@include file="header.jsp"%>
<div class="mid">
	<div class="container-background">
		<div class="crewregister">
			<div class="crew-header">크루 등록</div>
			<div class="crew-body">
				<form action="#" method="post">
					<label for="crewName">크루 이름</label> 
					<input type="text" id="crewName" name="crewName" placeholder="크루 이름을 입력하세요" required > 
					<label for="backgroundPhoto">크루 배경 사진</label> 
					<input type="file" id="backgroundPhoto" name="backgroundPhoto" accept="image/*">
					<label for="termType">원데이 크루 / 롱데이 크루</label> 
					<select id="termType" name="termType">
						<option value="longTerm">원데이 크루</option>
						<option value="shortTerm">롱데이 크루</option>
					</select>
					<button type="submit">등록 완료</button>
				</form>
			</div>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
