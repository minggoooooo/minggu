<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%
	LongCrewDAO dao = new LongCrewDAO();
	List<LongCrewRecruitDTO> crewList = dao.selectAllRecruit();	// 모든 장기크루 가져오기
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단키 크루 모집</title>
<link href="resources/css/layout.css" rel="stylesheet">
<link href="resources/css/CrewBoardList.css" rel="stylesheet">
</head>
<body>

	<div id="root">
		<div class="app">
			<div id="container">
				<!-- header.jsp 시작 -->
				<%@ include file="header.jsp"%>
				<!-- header.jsp 끝 -->
				<div id="contentWrapper">
					<div id="contentWrap">

						<div id="content">
							<div id="mypage">
								<h2 class="tit-page">단기 크루 모집</h2>
								
								<div class="page-body">
									<div class="info">
											<div class="main-con1 act">
												<div class="main-con-title">
													<h2></h2>
												</div>
												<div class="box">
													<ul>
													<!-- 추후 사용 예정 현재 비어둠 -->
														<li class=""><a
															href="#">
																<span class="img" aria-hidden="true"></span>
														</a></li>
														
													</ul>
												</div>
											</div>
											
											<div class="main-con2 act">
												<div class="main-con-title">
													<h2></h2>
												</div>
												<ul>
													<li><a
														href="CrewBoardList-OnedayActivity.jsp"
														class="btn-link arr"> <span class="img"
															aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/icon-maincon02-01.png"
																alt=""></span> <strong>단기크루 <span>활동 이동</span></strong>
													</a></li>
													<li><a
														href="CrewBoardList-LongActivity.jsp"
														class="btn-link arr"> <span class="img"
															aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/icon-maincon02-02.png"
																alt=""></span> <strong>장기크루 <span>활동 이동</span></strong>
													</a></li>
												</ul>
												<a href="CrewBoardList-LongGather.jsp"
													class="btn-link arr"><장기>크루 모집 보기 >> </a>
												
											</div>
									</div>
									<!-- //info -->
									<div class="info_box">
										<div class="content-area">
											<!-- forEach문 시작할 구역 -->
											<div class="main-con3">
												<div class="main-con-title">
													<h2>크루 활동 리스트</h2>
												</div>
												<ul class="main-con-event">
												
													<li><a href="#" onclick="#">
															<span class="img" aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/01_traveler_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="txt-01">등록일 </p><span>2023.12.15</span><!-- ${crew.created} -->
																<p class="txt-02">크루명 </p><span>무병장수</span><!-- ${crew.crewName} -->
																<p class="txt-02">소개(조건) </p><span>장수하실분</span><!-- ${crew.content} -->
																<p class="txt-02">모집인원 </p><span>8(현재크루원 값)</span>/<span>15(총 모집인원 값)</span><!-- ${crew.totalCount}/${crew.memberNum} -->
																<p class="txt-02">방장ID </p><span>momo1234</span><!-- ${crew.adminId} -->
															</div>
													</a></li>
												
												<!-- forEach문 마감할 구역 -->
												<!-- forEach문으로 크루 리스트 불러올 예정이지만, 임시로 9개 채워넣음 -->
													<li><a href="#" onclick="#">
															<span class="img" aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/01_traveler_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="txt-01">등록일 </p><span>2023.12.15</span>
																<p class="txt-02">크루명 </p><span>무병장수</span>
																<p class="txt-02">소개(조건) </p><span>장수하실분</span>
																<p class="txt-02">모집인원 </p><span>8(현재크루원 값)</span>/<span>15(총 모집인원 값)</span>
																<p class="txt-02">방장ID </p><span>momo1234</span>
															</div>
													</a></li>
													<li><a href="#" onclick="#">
															<span class="flag">모집마감임박</span> 
															<span class="img" aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/01_traveler_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="txt-01">등록일 </p><span>2023.12.15</span>
																<p class="txt-02">크루명 </p><span>무병장수</span>
																<p class="txt-02">소개(조건) </p><span>장수하실분</span>
																<p class="txt-02">모집인원 </p><span>8(현재크루원 값)</span>/<span>15(총 모집인원 값)</span>
																<p class="txt-02">방장ID </p><span>momo1234</span>
															</div>
													</a></li>
													<li><a href="#" onclick="#">
															<span class="img" aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/01_traveler_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="txt-01">등록일 </p><span>2023.12.15</span>
																<p class="txt-02">크루명 </p><span>무병장수</span>
																<p class="txt-02">소개(조건) </p><span>장수하실분</span>
																<p class="txt-02">모집인원 </p><span>8(현재크루원 값)</span>/<span>15(총 모집인원 값)</span>
																<p class="txt-02">방장ID </p><span>momo1234</span>
															</div>
													</a></li>
													<li><a href="#" onclick="#">
															<span class="img" aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/01_traveler_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="txt-01">등록일 </p><span>2023.12.15</span>
																<p class="txt-02">크루명 </p><span>무병장수</span>
																<p class="txt-02">소개(조건) </p><span>장수하실분</span>
																<p class="txt-02">모집인원 </p><span>8(현재크루원 값)</span>/<span>15(총 모집인원 값)</span>
																<p class="txt-02">방장ID </p><span>momo1234</span>
															</div>
													</a></li>
<!-- 												<li><a href="#" onclick="#">
															<span class="flag">상품권</span> <span class="img"
															aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/03_meritzpet_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="event-tit">메리츠 펫보험 가입&amp;결제 시 최대 3만원
																	신세계상품권 혜택</p>
																<span>2023.11.01 ~ 2023.12.31</span>
															</div>
													</a></li>
													<<li><a href="#" onclick="#">

															<span class="img" aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/01_traveler_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="txt-01">등록일 </p><span>2023.12.15</span>
																<p class="txt-02">크루명 </p><span>무병장수</span>
																<p class="txt-02">소개(조건) </p><span>장수하실분</span>
																<p class="txt-02">모집인원 </p><span>8(현재크루원 값)</span>/<span>15(총 모집인원 값)</span>
																<p class="txt-02">방장ID </p><span>momo1234</span>
															</div>
													</a></li>
													<li><a href="#" onclick="#">

															<span class="img" aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/02_two-wheeler_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="txt-01">등록일 </p><span>2023.12.15</span>
																<p class="txt-02">크루명 </p><span>무병장수</span>
																<p class="txt-02">소개(조건) </p><span>장수하실분</span>
																<p class="txt-02">모집인원 </p><span>8(현재크루원 값)</span>/<span>15(총 모집인원 값)</span>
																<p class="txt-02">방장ID </p><span>momo1234</span>
															</div>
													</a></li>
													<li><a href="#" onclick="#">
															<span class="flag">상품권</span> <span class="img"
															aria-hidden="true"><img
																src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/03_meritzpet_510x336.png"
																alt=""></span>
															<div class="event-txt">
																<p class="txt-01">등록일 </p><span>2023.12.15</span>
																<p class="txt-02">크루명 </p><span>무병장수</span>
																<p class="txt-02">소개(조건) </p><span>장수하실분</span>
																<p class="txt-02">모집인원 </p><span>8(현재크루원 값)</span>/<span>15(총 모집인원 값)</span>
																<p class="txt-02">방장ID </p><span>momo1234</span>
															</div>
													</a></li> -->
												</ul>
												<a href="/insurance/UISRCO0401M0.jsp?click=ins_gnb_event"
													class="btn-link arr">전체보기</a>
											</div>

										</div>
										<!-- 페이지 처리 -->
										<div id="pagination">
										<div class="paging-box">
										<%-- 	<c:set var="pageNum" value="<%=pageNum%>"/>
											<div>
												<c:forEach var="i" begin="1" end="<%=blockSize%>">
													<c:url value="/board.jsp" var="url"/>
													<c:choose>
														<c:when test="${pageNum==i}">
															<a style="text-decoration: none;" href="${url}?pageNum=${i}&crewName=${crewName}"><font color='black'>${i}</font></a>
														</c:when>
														<c:otherwise>
															<a style="text-decoration: none;" href="${url}?pageNum=${i}&crewName=${crewName}"><font color='gray'>${i}</font></a>
														</c:otherwise>
													</c:choose>	
												</c:forEach>
											</div> --%>
										<p>< 1 / 2 / 3 / 4 / 5 ></p>	<!-- 페이징 처리 주석 대체(임시 적용):페이징 처리가 필요한 것 같은데 언니 파일에 없어서 일단 그냥 태균이꺼에서 가져왔어요 -->
											</div> <!-- paging-box -->
										</div>	<!-- pagination -->
									</div> <!-- info-box -->
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
</body>
</html>