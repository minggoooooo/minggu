<%@page import="java.time.Duration"%>
<%@page import="model.CourseDAO"%>
<%@page import="model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.MyCourseDAO"%>
<%@page import="model.MyCourse"%>
<%@page import="model.CrewJoinDTO"%>
<%@page import="model.CrewJoinDAO"%>
<%@page import="model.CrewRecruitDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.CrewRecruitDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="loginCheck.jsp" %>
<%
	String UserId = (String)session.getAttribute("userId");
	CrewRecruitDAO dao = new CrewRecruitDAO();
	List<CrewRecruitDTO> crewList = dao.selectPlannedSchedule(UserId);		// 나의 예정된 일정
	CrewRecruitDTO crew = crewList.get(0);
	CrewJoinDAO dao2 = new CrewJoinDAO();
	MyCourseDAO dao3 = new MyCourseDAO();
	CourseDAO dao4 = new CourseDAO();
	List<CrewJoinDTO> joinList = dao2.selectAllCrewJoin(UserId);			// 나의 승인현황
	List<CrewJoinDTO> joinList2 = dao2.selectAllJoin(UserId);				// 나의 승인할 크루가입 리스트
	ArrayList<String> courses = dao3.getCourses(UserId);
	// 코스 아이디 가져오고 
	Duration totalDuration = Duration.ZERO; // 총 시간을 저장할 Duration 객체
	double totalDistance = 0.0;

	for(String courseId : courses){
		Course course = dao4.bringCourse(courseId);
		
		 String[] split = course.getExpect_time().split(" ");
         int hours = Integer.parseInt(split[0].replace("시간", ""));
         int minutes = split.length > 1 ? Integer.parseInt(split[1].replace("분", "")) : 0;
		
         Duration duration = Duration.ofHours(hours).plusMinutes(minutes);
         totalDuration = totalDuration.plus(duration);
         
         double distance = Double.parseDouble(course.getLength().replace("km", ""));
         totalDistance += distance;
	}
	
	 long totalHours = totalDuration.toHours();
     long totalMinutes = totalDuration.toMinutes() % 60;
	
	dao.close();
	dao2.close();
	dao3.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 페이지</title>
<link href="./resources/css/layout.css" rel="stylesheet">
<link href="./resources/css/MyPage.css" rel="stylesheet">
</head>
<body>
	<h3> 총시간 : <%=totalHours %> 시간 <%=totalMinutes %>분</h3>
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
									<h2>
										안녕하세요, <span class="userName"><%=UserId%></span>님!
									</h2>

								</div>
								<div class="page-body">
									<div class="info">

										<div class="order">
											<div id="profile_i"></div>
											<dl>
												<dt>누적거리 (단위: km)</dt>
												<dd class="ico3">
													<strong><%=totalDistance %></strong>
												</dd>
											</dl>
											<dl>
												<dt>누적시간</dt>
												<dd class="ico3">
													<strong><%=totalHours %>시간 <%=totalMinutes %>분</strong>
												</dd>
											</dl>


											<dl>
												<dt>뱃지</dt>
												<dd class="ico1">
													<strong>2</strong>
												</dd>
											</dl>

										</div>
									</div>
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
												<h3>나의 예정된 일정</h3>
												<a class="CSSbutton"
													href="MyPage-plan-schedule.jsp">+ MORE</a>
											</div>
											<div class="tbl Row">
												<colgroup>
													<col width="100%">
												</colgroup>
												<div class="tbl-01">
													<table>
														<tr>
															<td class="txt-H">크루명</td>
															<td class="txt-C"><%=crew.getCrewName()%></td>
														</tr>
														<tr>
															<td class="txt-H">크루 일정</td>
															<td class="txt-C"><%=crew.getGatherDate()%></td>
														</tr>
														<tr>
															<td class="txt-H">목표 코스</td>
															<td class="txt-C"><%=crew.getCourseId()%></td>
														</tr>
													</table>
												</div>
												<div class="tbl-02 Col-C">
													<a href="#"><div class="detail">상세보기 ></div></a>
												</div>

											</div>

											<div class="info_cont-02">
												<div class="bx_list">
													<div class="hd">
														<h3>가입신청현황</h3>
														<a class="CSSbutton"
															href="MyPage-sub3.jsp">+
															MORE</a>
													</div>
													
													<c:forEach items="<%=joinList%>" var="join" end="1">
													<div class="look_wrap-01">
														<div class="look-100">
															<div class="look-bx Row">
																<div>
																	<p class="txt-H">크루명</p>
																	<p class="txt-C-L">${join.crewName}</p>
																</div>
																<div class="Col">
																	<c:if test="${join.isCheck eq 1}">
																		<p class="join-sta">승인 대기</p>
																	</c:if>
																	<c:if test="${join.isCheck eq 2}">
																		<p class="join-sta">승인 완료</p>
																	</c:if>
																	<c:if test="${join.isCheck eq 3}">
																		<p class="join-sta">승인 거절</p>
																	</c:if>
																</div>
															</div>
														</div>
													</div>
													</c:forEach>
													

												</div>

												<div class="bx_list">
													<div class="hd">
														<h3>개설현황</h3>
														<a class="CSSbutton"
															href="MyPage-sub4.jsp">+
															MORE</a>
													</div>
													<div class="look_wrap-02">
														
														<c:forEach items="<%=joinList2%>" var="join">
														<div class="look-50">
															<div class="look-bx">
																<div>
																	<ul>
																		<li class="txt-H-01"><div class="join-num">
																				(
																				<p>모집인원</p>
																				:
																				<p>${join.memberNum}</p>
																				)
																			</div></li>
																		<li class="txt-H">크루명 :</li>
																		<li class="txt-C">${join.crewName}</li>
																		<li class="txt-I"><img
																			src="./resources/images/user2.png" width="100px"
																			height="100px"></li>
																		<li class="txt-H">가입 신청 ID :</li>
																		<li class="txt-C">${join.memId}</li>
																	</ul>
																</div>
																<!-- <div class="sign-btn">
																	<button class="join_btn Y" value="yes">수락</button>
																	<button class="join_btn N" value="no">거절</button>
																</div> -->
															</div>
														</div>
														</c:forEach>
														
													</div>
												</div>
												<button id="prevButton">이전</button>
												<button id="nextButton">다음</button>
												<!-- bx-list -->
											</div>
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
				<script type="text/javascript">
				
				// look-50 의 모든 요소를 가져옴
				const elements = document.querySelectorAll('.look-50');
				const prevButton = document.getElementById('prevButton');
				const nextButton = document.getElementById('nextButton');
				let currentIndex = 0;
				
				
				// 숨겨놨던걸 꺼내기 위해서 기본상태는 display none이라 block을 가지고 있는 show라는 클래스를 추가할거임
				function showElements(startIndex) {
					// 요소의 개수만큼 반복할거에요 
				  for (let i = 0; i < elements.length; i++) {
					  // 시작하는 인덱스에서 2개씩 보여지게 할거임
				    if (i >= startIndex && i < startIndex + 2) {
				      elements[i].classList.add('show');
				    } else {
				      elements[i].classList.remove('show');
				    }
				  }
				}
				
				// 버튼 숨기고 나타내기
				function updateButtons() {
				  if (currentIndex === 0) {
				    prevButton.classList.add('hide');
				  } else {
				    prevButton.classList.remove('hide');
				  }

				  if (currentIndex >= elements.length - 2) {
				    nextButton.classList.add('hide');
				  } else {
				    nextButton.classList.remove('hide');
				  }
				}
				
				// 다음을 누르면
				function nextElements() {
				  if (currentIndex < elements.length - 2) {
				    currentIndex++;
				    showElements(currentIndex);
				    updateButtons();
				  }
				}
				
				// 이전을 누르면
				function prevElements() {
				  if (currentIndex > 0) {
				    currentIndex--;
				    showElements(currentIndex);
				    updateButtons();
				  }
				}

				prevButton.addEventListener('click', prevElements);
				nextButton.addEventListener('click', nextElements);
				
				//처음 화면 나왔을 때 실행하려고 쓴거에옹
				showElements(currentIndex); 
				updateButtons();
				</script>
				<%@ include file="footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>