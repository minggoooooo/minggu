<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title></title>

<link href="resources/css/layout.css" rel="stylesheet">
<link href="resources/css/trangle.css" rel="stylesheet">
</head>
<body>

	<div id="app">

		<div class="wrap-course">
			<%@ include file="header.jsp"%>
			<div class="mid">
				<div class="course">
					<div class="course_nav"></div>
					<div class="course_left">
						<div class="tab_menu">
							<ul>
								<li class="on"><a href="#"><span class="nav01"></span><span
										class="text">산책</span></a></li>
								<li class=""><a><span class="nav02"></span><span
										class="text">자전거</span></a></li>
							</ul>
						</div>
						<div class="section">
							<div class="search">
								<input type="checkbox" id="i_option"> <label
									for="i_option"> <span></span> <span></span> <span></span>
								</label> <span class="search_input input_def"> <input type="text"
									placeholder="주소 or 코스를 검색하세요"> <a href="#"
									class="i_fin" style="display: block;"></a>
								</span>
							</div>
							<!-- 수정중 영역 start-->
							<!--  <div class="option_tag">
								<span class="option_choice"> <a href="#"> -->



							<!-- </a>
								</span>
							</div> -->
							<div class="opt-hidden">
								<form id="optForm" method="get" action="#">
									<span class="txt-T"> * 반경범위 : </span>
									<div class="wrap-range">
										<div class="range">
											<div class="sliderValue">
												<span>100</span>
											</div>
											<div class="field">
												<div class="value left">0</div>
												<input type="range" min="10" max="100" value="50" step="10">
												<div class="value right">100</div>
											</div>
										</div>

									</div>
									<span class="txt-T"> * 정렬순서 : </span>
									<div class="wrap-order">
										<div class="order">
											<label class="check"> <input type="radio" name="array" value="time-high-order"> 
												<span class="i_check"></span> <span>소요시간 높은 순</span></label> 
											<label class="check"> <input type="radio" name="array" value="time-row-order"> 
												<span class="i_check"></span> <span> 소요시간 낮은 순</span>  </label>
										</div>
										<div class="order">
											<label class="check"> <input type="radio"
												name="array" value="distance-high-order"> <span
												class="i_check"></span> <span>전체거리 높은 순</span>
											</label> <label class="check"> <input type="radio"
												name="array" value="distance-row-order"> <span
												class="i_check"></span> <span>전체거리 낮은 순</span>
											</label>
										</div>
									</div>
									<div class="wrap-button"><button type="submit">선택완료</button></div>
								</form>
							</div>


							<!-- 수정중 영역 finish -->
						</div>
						<script>
								    const slideValue = document.querySelector(".sliderValue span");
								    const inputSlider = document.querySelector("input[type=range]");
								
								    inputSlider.addEventListener("input", () => {
								        let value = inputSlider.value;
								        slideValue.textContent = value;
								        slideValue.style.left = (value / inputSlider.max) * 100 + "%";
								        slideValue.classList.add("show");
								    });
								 
								    inputSlider.addEventListener("change", () => {
								        slideValue.classList.remove("show");
								    });
								    
								 
							        const checkbox = document.getElementById('i_option');
							        const hiddenDiv = document.querySelector('.opt-hidden');
							    
							
							        checkbox.addEventListener('change', () => {
							            hiddenDiv.style.display = checkbox.checked ? 'block' : 'none';
							        });
							      </script>
						<!-- 	/* 
							   const optForm = document.getElementById('optForm');
							        optForm.addEventListener('submit', (event) => {
							            // form이 제출되면 천천히 숨겨지도록 설정
							            event.preventDefault(); // 기본 제출 동작 막기
							            setTimeout(() => {
							                hiddenDiv.style.display = 'none';
							                optForm.submit(); // 숨겨진 후 form 다시 제출
							            }, 1000); // 1000ms (1초) 후에 숨김
							        });
							     */ -->


						<div class="left_content" style="height: calc(100vh - 245px);">
							<div class="top_title">< COURSE LIST ></div>
							<div class="list_poi">
								<ul>
									<li id="list_0" class=""><span class="td1"></span> <span
										class="td3"> <!-- 	<span class="t_icon"> 
											</span>  --> <span class="poi_content"> <span
												class="subject"> <span class="text elp">경기도_시흥시_20231202_193414</span>
											</span> <span class="data"> <span class="text"><span
														class="t1">전체 거리</span>3.8 km</span> <span class="text"><span
														class="t1">소요 시간</span>01:29:35</span> <span class="text"><span
														class="t1">난이도</span>하</span>
											</span>

										</span>
									</span> <!----> <a href="#"> <span class="t_block"> <span
												class="i_like"></span> <span class="text">0</span>
										</span>
									</a></li>
									<li id="list_0" class=""><span class="td1"></span> <span
										class="td3"> <!-- 	<span class="t_icon"> 
											</span>  --> <span class="poi_content"> <span
												class="subject"> <span class="text elp">경기도_시흥시_20231202_193414</span>
											</span> <span class="data"> <span class="text"><span
														class="t1">전체 거리</span>3.8 km</span> <span class="text"><span
														class="t1">소요 시간</span>01:29:35</span> <span class="text"><span
														class="t1">난이도</span>하</span>
											</span>

										</span>
									</span> <!----> <a href="#"> <span class="t_block"> <span
												class="i_like"></span> <span class="text">0</span>
										</span>
									</a></li>
									<li id="list_0" class=""><span class="td1"></span> <span
										class="td3"> <!-- 	<span class="t_icon"> 
											</span>  --> <span class="poi_content"> <span
												class="subject"> <span class="text elp">경기도_시흥시_20231202_193414</span>
											</span> <span class="data"> <span class="text"><span
														class="t1">전체 거리</span>3.8 km</span> <span class="text"><span
														class="t1">소요 시간</span>01:29:35</span> <span class="text"><span
														class="t1">난이도</span>하</span>
											</span>

										</span>
									</span> <!----> <a href="#"> <span class="t_block"> <span
												class="i_like"></span> <span class="text">0</span>
										</span>
									</a></li>
									<li id="list_0" class=""><span class="td1"></span> <span
										class="td3"> <!-- 	<span class="t_icon"> 
											</span>  --> <span class="poi_content"> <span
												class="subject"> <span class="text elp">경기도_시흥시_20231202_193414</span>
											</span> <span class="data"> <span class="text"><span
														class="t1">전체 거리</span>3.8 km</span> <span class="text"><span
														class="t1">소요 시간</span>01:29:35</span> <span class="text"><span
														class="t1">난이도</span>하</span>
											</span>

										</span>
									</span> <!----> <a href="#"> <span class="t_block"> <span
												class="i_like"></span> <span class="text">0</span>
										</span>
									</a></li>
									<li id="list_0" class=""><span class="td1"></span> <span
										class="td3"> <!-- 	<span class="t_icon"> 
											</span>  --> <span class="poi_content"> <span
												class="subject"> <span class="text elp">경기도_시흥시_20231202_193414</span>
											</span> <span class="data"> <span class="text"><span
														class="t1">전체 거리</span>3.8 km</span> <span class="text"><span
														class="t1">소요 시간</span>01:29:35</span> <span class="text"><span
														class="t1">난이도</span>하</span>
											</span>

										</span>
									</span> <!----> <a href="#"> <span class="t_block"> <span
												class="i_like"></span> <span class="text">0</span>
										</span>
									</a></li>
									<li id="list_0" class=""><span class="td1"></span> <span
										class="td3"> <!-- 	<span class="t_icon"> 
											</span>  --> <span class="poi_content"> <span
												class="subject"> <span class="text elp">경기도_시흥시_20231202_193414</span>
											</span> <span class="data"> <span class="text"><span
														class="t1">전체 거리</span>3.8 km</span> <span class="text"><span
														class="t1">소요 시간</span>01:29:35</span> <span class="text"><span
														class="t1">난이도</span>하</span>
											</span>

										</span>
									</span> <!----> <a href="#"> <span class="t_block"> <span
												class="i_like"></span> <span class="text">0</span>
										</span>
									</a></li>
									<li id="list_0" class=""><span class="td1"></span> <span
										class="td3"> <!-- 	<span class="t_icon"> 
											</span>  --> <span class="poi_content"> <span
												class="subject"> <span class="text elp">경기도_시흥시_20231202_193414</span>
											</span> <span class="data"> <span class="text"><span
														class="t1">전체 거리</span>3.8 km</span> <span class="text"><span
														class="t1">소요 시간</span>01:29:35</span> <span class="text"><span
														class="t1">난이도</span>하</span>
											</span>

										</span>
									</span> <!----> <a href="#"> <span class="t_block"> <span
												class="i_like"></span> <span class="text">0</span>
										</span>
									</a></li>
								</ul>
							</div>

						</div>

					</div>
					<div class="content">
						<img src="./resources/images/img_screenshot 2023-11-26 175812.png"
							style="width: 100%; height: 100%;">
					</div>
					<div class="course_nav"></div>

				</div>
			</div>
			<%@ include file="footer.jsp"%>
		</div>
	</div>
</body>

</html>