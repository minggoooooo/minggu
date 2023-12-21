<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단기 크루 등록</title>
<link href="resources/css/layout.css" rel="stylesheet">
<link href="resources/css/crewRecruit.css" rel="stylesheet">
<link href="resources/css/edit_MemINFO.css" rel="stylesheet">
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
								<h2 class="tit-page">단기 크루 등록</h2>
								<div class="page-body">
									<form action="" method="post" name="frm">
										<div class="info">
											<div class="order">

												<!-- input영역 전 '아이디' 텍스트 영역 -->
												<div class="wrap">
													<h6 class="mm_text-label">
														<b> 크루명 </b>
													</h6>
													<!-- '아이디' input 영역 -->
													<!-- label태그로 input박스 안의 텍스트(위치, 정렬 등)요소가 설정되어 있음 -->
													<!-- i태그로 input 박스의 테두리가 설정되어 있음 -->
													<div style="width: 80%">
													<div class="mm_frm-text">
														<label id="Ro-01"> <input type="text"
															id="input_id" maxlength="12" class="textfield"
															placeholder="크루 이름을 입력해주세요" name="crewName" required>
															<i class="bg_text"></i></label>
													<button class="btn_dw-check" onclick="crewNameCheck()">중복확인</button>
													</div>
													</div>
												</div>
													<div class="mm_note" id="warningMessageContainer">
														<h6 class="mm_text-label">
															<b></b>
														</h6>
														<div class="">
															<p>동일한 크루명이 존재합니다. 다른 크루명을 입력해주세요</p>
														</div>
													</div>



												<div class="wrap">
													<h6 class="mm_text-label">
														<b>한줄 소개(제목)</b>
													</h6>
													<div class="mm_form-text">
														<label><input type="text" maxlength="16"
															class="textfield" placeholder="크루 소개 제목을 입력해주세요"
															name="title"><i class="bg_text"></i> </label>
													</div>
												</div>
												<div class="wrap">
													<h6 class="mm_text-label">
														<b>모집 조건(내용)</b>
													</h6>
													<div class="mm_form-text">
														<label><input type="text" maxlength="20"
															class="textfield"  placeholder="해당 일정에 모집할 내용을 입력해주세요"><i
															class="bg_text" name="location" required></i> </label>
													</div>
												</div>
												<div class="wrap">
													<h6 class="mm_text-label">
														<b>장소</b>
													</h6>
													<div class="mm_form-text">
														<label><input type="text" maxlength="20"
															class="textfield" placeholder="모일 장소를 입력해주세요"><i
															class="bg_text" name="location" required></i> </label>
													</div>
												</div>
												<div class="wrap">
													<h6 class="mm_text-label">
														<b>모집 인원</b>
													</h6>
													<div class="mm_form-text">
														<label><input type="number" class="textfield"
															placeholder="모집 할 인원수를 입력해주세요" name="memberNum" required
															min="2" maxlength="100"><i class="bg_text"></i> </label>
													</div>
												</div>

												<div class="wrap">
													<h6 class="mm_text-label">
														<b>모임 일정</b>
													</h6>
													<div class="mm_form-text">
														<label><input type="date"
															oninvalid="this.setCustomValidity('달력을 클릭하세요')" class="textfield"
															data-placeholder="달력을 클릭하세요" name="gatherDate" required><i class="bg_text"></i></label>
													</div>
												</div>
												<div class="wrap">
													<h6 class="mm_text-label">
														<b>코스명</b>
													</h6>
													<div class="mm_form-text">
														<label><input type="text" maxlength="20"
															class="textfield" placeholder="코스명을 입력해주세요"
															name="courseId" required><i class="bg_text"></i>
														</label>
													</div>
												</div>

												<div class="mm_foot">
													<div class="mm_btnbox">
														<div class="mm_block">
															<input type="submit" value="변경하기"
																class="mm_btn __btn_lg_primary__">
															<!-- <button type="button" >
													<b>수정하기</b>
												</button> -->
														</div>
													</div>
												</div>

												<!-- #mypage -->
											</div>
											<!-- order -->
											<!-- #content -->
										</div>
									</form>

								</div>
								<%@ include file="footer.jsp"%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>