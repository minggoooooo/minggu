<%@page import="model.CrewJoinDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.CrewJoinDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="loginCheck.jsp" %>
<%
	String UserId = (String)session.getAttribute("userId");
	CrewJoinDAO dao = new CrewJoinDAO();
	List<CrewJoinDTO> joinList = dao.selectJoinByAdmin(UserId);
	pageContext.setAttribute("joinList", joinList);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개설현황(단기크루)</title>
<link href="resources/css/layout.css" rel="stylesheet">
<link href="resources/css/MyPage2.css" rel="stylesheet">
</head>
<body>

	<div id="root">
		<div class="app">
			<div class="container">
				<!-- header.jsp 시작 -->
				<%@ include file="header.jsp"%>
				<!-- header.jsp 끝 -->
				<div id="contentWrapper">
					<div id="contentWrap">

						<div id="content">
							<div id="mypage">
								<h2 class="tit-page">마이페이지</h2>
								<div class="my_info">
								<div id="profile_i"></div>
									<h2>
										안녕하세요, <span class="userName"><%=UserId%></span>님!
									</h2>

								</div>
								<div class="page-body">
				
									<!-- //info -->
									<div class="info_box">
										<div class="mypage_menu">
											<ul>
												<h2>나의 크루활동</h2>
												<h4>가입신청현황</h4>
												<li><a href="MyPage-sub3.jsp">- 단기크루</a></li>
												<li><a href="MyPage-sub3-longCrew.jsp">- 장기크루</a></li>
												<h4>개설현황</h4>
												<li><a href="MyPage-sub4.jsp">- 단기크루</a></li>
												<li><a href="MyPage-sub4-longCrew.jsp">- 장기크루</a></li>
												<h4>활동 중인 크루</h4>
												<li><a href="MyPage-ongoing.jsp">- 단기크루</a></li>
												<li><a href="MyPage-ongoing-longCrew.jsp">- 장기크루</a></li>

												<h4>일정 관리 현황</h4>
												<li><a href="MyPage-plan-schedule.jsp">- 예정된 일정</a></li>
												<li><a href="MyPage-fin-schedule.jsp">- 마감된 일정</a></li>

											</ul>
											<ul>
												<h2>나의 정보 관리</h2>
												<h4>
													<a href="edit-MemINFO.jsp">회원정보 변경</a>
												</h4>
												<h4>
													<a href="withdrawal.jsp">회원 탈퇴</a>
												</h4>
											</ul>
										</div>


										<div class="info_cont">
											<div class="hd">
												<h3>개설현황(단기크루)</h3>
											</div>
											
											
											<c:choose>
											<c:when test="${not empty joinList}">
											
											<c:forEach items="<%=joinList%>" var="join">
											<form action="./joinCrew.join" method="post" name="frm">
												<div class="tbl Row">
													<colgroup>
														<col width="100%">
													</colgroup>
													<img src="./resources/images/user2.png" width="100px"
													height="100px">
													<div class="blank" style="width: 50px;"></div>
													
													<div class="tbl-01">
														<table>
															<!-- <tr>
																<td class="txt-H-01">
																<div class="join-num">
																	(
																	<p>8</p>
																	/
																	<p>15</p>
																	) 인원
																</div>
																</td>
															</tr> -->
															<tr>
																<td class="txt-H">크루명</td>
																<td class="txt-C">${join.crewName}</td>
															</tr>
															<tr>
																<td class="txt-H">신청인 ID</td>
																<td class="txt-C">${join.memId}</td>
															</tr>
															<tr>
																<td class="txt-H">성별</td>
																<td class="txt-C">${join.gender}</td>
															</tr>
														</table>
													</div>
													<input type="hidden" name="crewName" value="${join.crewName}">
													<input type="hidden" name="adminId" value="${join.adminId}">
													<input type="hidden" name="memId" value="${join.memId}">
													<input type="hidden" name="memberNum" value="${join.memberNum}">
													<input type="hidden" name="isShortCrew" value="${join.isShortCrew}">
													<input type="hidden" name="isPermit">
													
													
													<div class="tbl-02 Col-C">
														<button type="button" onclick="check(this, true)" class="join_btn Y">수락</button>
														<button type="button" onclick="check(this, false)" class="join_btn N">거절</button>
													</div>
	
	
												</div>
											</form>
											</c:forEach>
											
											</c:when>
											<c:otherwise>
												<h2>개설한 크루에 신청 현황이 없습니다</h2>
											</c:otherwise>
											</c:choose>
												

										</div>
									</div>
								</div>
							</div>
							<!-- .page-body -->
						</div>
						<!-- #mypage -->
					</div>
					<!-- #content -->
				</div>
				<%@ include file="footer.jsp"%>
			</div>
		</div>
	</div>
	
	
	<!-- 수락 or 거절 버튼 확인 -->
	<script>
		var fr;
		function check(th, bool) {
			fr = th.closest('form');
			console.log(fr);
			fr.querySelector('input[name=isPermit]').value = bool;
			console.log(fr);
			fr.submit();
		}
	</script>
</body>
</html>