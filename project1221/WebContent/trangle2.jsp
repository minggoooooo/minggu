<%@page import="model.CourseRecommendDAO"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="model.CourseDAO"%>
<%@page import="controller.GpxParser"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@page import="model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%
	response.setCharacterEncoding("utf-8");
CourseDAO dao = new CourseDAO();
CourseRecommendDAO dao2 = new CourseRecommendDAO();
// 코스 리스트 받아오기
ArrayList<Course> list = new ArrayList<>();

// 이거는 정렬때문에 넣어놓은거
if (request.getAttribute("selectedValue") != null) {
	String selectedValue= (String) request.getAttribute("selectedValue");
	switch(selectedValue){
	case "time-high-order" :
		list = dao.LoadCourseTimeDesc();
		break;
	case "time-row-order" :
		list = dao.LoadCourseTimeAsc();
		break;
	case "distance-high-order" :
		list = dao.LoadCourseDistanceDesc();
		break;
	default :	
		list = dao.LoadCourseDistanceAsc();
	}
	
} else {
	list = dao.LoadCourse();
	// 초기 값 or 정렬 기준 없을 때
}
// 위치 받아오기
String address= "초기 위치";
if (request.getAttribute("address") != null) {
	address = (String) request.getAttribute("address");
} 
int selectedBar = 100;

// 바 값 그대로 놔두기 위해서
if (request.getAttribute("selectedBar") != null) {
	selectedBar = Integer.parseInt((String) request.getAttribute("selectedBar"));
}
// 파일을 넣을 리스트
ArrayList<File> fileList = new ArrayList<>();

// 검색할 디렉토리 경로
String directoryPath = "C:\\paths";
File directory = new File(directoryPath);
//디렉토리에 있는 파일들 리스트 얘들은 정렬순서가 지 맘대로임
// 파일이 있는 코스 리스트만 정렬
File[] files = directory.listFiles();

// 받아온 루트 이름들
String[] routelistName = (String[]) request.getAttribute("routeList");
// first 페이지에서 받아온 거리값
String[] routeForDistance = (String[]) request.getAttribute("routeForDistance");

//나와의 거리 
ArrayList<Double> distances = new ArrayList<>();
//루트이름에 맞는 코스리스트들 (목록에 표시할 애들)
ArrayList<Course> routelist = new ArrayList<>();

// 가져온 루트리스트(루트의 파일이름)으로 모든 코스와 비교하여
// 파일이름이 같다면 추가해줌
for (Course course : list) {

	for (int i = 0; i < routelistName.length; i++) {
    double distance = Double.parseDouble(routeForDistance[i]);
	String routename = routelistName[i];
		if (course.getFile_id().equals(routename)) {
			course.setDistance(distance);
			routelist.add(course);
		}
	}
}

//routelist에 recommend 설정해주기
for(Course course : routelist){
	course.setRecommend(dao2.getRecommend(course.getId()));
}


//routelist를 거리를 기준으로 정렬	(기준값 없을 때)
if (request.getAttribute("selectedValue") == null) {
Collections.sort(routelist, Comparator.comparingDouble(Course::getDistance));
}

// 얘는 gpx파일이 있는 코스리스트들 즉 db와 연동시켜서 가져온 파일이름이 실제로 있는지 매칭이 된 리스트임 
ArrayList<Course> isFileList = new ArrayList<>();


for (Course course : list) {

	// db의 파일이름이랑 같은지 확인
	String targetFileName = course.getFile_id();
	for (File file : files) {
		if (file.isFile() && file.getName().equals(targetFileName)) {
	// 원하는 파일을 찾았을 때, 해당 파일을 사용하면 됩니다.
			isFileList.add(course);
			fileList.add(file);
		}
	}

}

for(int i =0; i<isFileList.size(); i++){	//i라는 인덱스를 쓰기 위해서 일반 for문으로 작성
	for(Course course : routelist){
		if(course.getId().equals(isFileList.get(i).getId())){
			course.setIdxNum(i);
		}
	}
}
dao.close();
dao2.close();
// 파일이 여러개니 한 파일 한 파일 파싱을 위하여 map을 만든뒤 gpxparser라는 자바파일 (gpx를 XML로 파싱)
//key에는 파일이름 value에는 파싱한 경로가 들어있어요
Map<String, ArrayList<String>> coordinates = GpxParser.parseGpx(fileList);

// 배열을 그대로 JS파일에 넘기고 싶어서 만들었음.
String[] coordinateKeys = coordinates.keySet().toArray(new String[0]);

//이차원 배열이라 조금 복잡해유
String[][] coordinateValues = coordinates.values().stream().map(list2 -> list2.toArray(new String[0]))
		.toArray(String[][]::new);
%>
<title>코스 찾기 페이지 2</title>
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=5b706854aedb7b02e16f877971562d29&libraries=services,drawing"></script>
<link rel="stylesheet" href="./resources/css/map3.css">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link href="resources/css/layout.css" rel="stylesheet">
<link href="resources/css/trangle.css" rel="stylesheet">
</head>
<script type="text/javascript">
	// 오버레이 띄우기 위해서 필요함.
	 <%-- var login_id = <%= session.getAttribute("login_id") %>; --%>
	 var items = <%=new Gson().toJson(isFileList)%>;
	 var keys = <%=new Gson().toJson(coordinateKeys)%>;
	 var values = <%=new Gson().toJson(coordinateValues)%>;
	 // 위치값 받아오려고 씀
	 var firstlat = ${currentlat}
	 var firstlng = ${currentlng}
  </script>
<body onload="getRoute()">
	<!-- 헤더 푸터 포함 전체 페이지 감싸는 영역 -->
	<div id="app">
		<!-- 헤더 푸터 포함 전체 페이지 -->
		<div class="wrap-course">
			<%@ include file="header.jsp"%>
			<!-- 코스 페이지 전체(헤더 푸터 제외) 감싸는 영역 -->
			<div class="mid">
				<!-- 코스 페이지 전체(헤더 푸터 제외) -->
				<div class="course">
					<!-- 코스 검색하는 구역 전체 옆 여백  -->
					<div class="course_nav"></div>
					<!-- 코스 검색하는 구역 전체  -->
					<div class="course_left">
						<!-- 산책, 자전거 탭 감싸는 영역 -->
						<div class="tab_menu">
							<ul>
								<!-- 산책 탭 영역 -->
								<li class="on"><a href="#"><span class="nav01"></span><span
										class="text">산책</span></a></li>
								<!-- 자전거 탭 영역 -->
								<li class=""><a><span class="nav02"></span><span
										class="text">자전거</span></a></li>
							</ul>
						</div>
						<!-- 산책, 자전거 탭 감싸는 영역 끝남 -->
						<!-- 주소 검색 영역(옵션 설정 영역 포함) -->
						<div class="section">
							<!-- 옵션버튼 + 검색창 -->
							<c:set var="address" value="<%=address %>"/>
							<%-- <div class="search">
								<!-- 옵션버튼 아이콘 -->
								<a href="#" class="i_opt" style="display: block;"></a>
								<!-- 검색창(검색input + 검색아이콘)  -->
								<span class="search_input input_def"> <input type="text"
									id="addressInput" placeholder="${address }" readonly> <a
									href="#" class="i_fin" onclick="getAddress();"
									style="display: block;"></a>
								</span>
							</div> --%>
							<div class="search">
                        <input type="checkbox" id="i_option"> <label
                           for="i_option"> <span></span> <span></span> <span></span>
                        </label> <span class="search_input input_def"> <input type="text"
                           placeholder="주소 or 코스를 검색하세요"> <a href="#"
                           class="i_fin" style="display: block;"></a>
                        </span>
							
							<!-- 수정중 영역 start-->
							<!--  <div class="option_tag">
								<span class="option_choice"> <a href="#"> -->
							<!-- 줄3개 옵션창 열리는 버튼 -->
							<input type="checkbox" id="i_option"> <label
								for="i_option"> <span></span> <span></span> <span></span>
							</label>
							<!-- 줄3개 옵션창 열리는 버튼 끝남-->

							<!-- </a>
								</span>
							</div> -->
							<!-- 옵션창 (처음에는 hidden상태-누르면 열리는 구역) -->
							<div class="opt-hidden">
								<form action="LocationServlet2" method="post"
									id="geolocationForm" onsubmit="submitForm(); return false;">
									<span> 반경범위(단위: km) : </span>
									<!-- 반경 범위 설정하는 구역(range)를 감싸는 구역 -->
									<div class="wrap-range">
										<!-- 반경 범위 설정하는 구역(슬라이더 + 슬라이도 이동버튼) -->
										<div class="range">
											<!-- range에서 슬라이더 옮기는 버튼?영역 -->
											<div class="sliderValue">
												<!-- range에서 슬라이더 옮기는 버튼내에 임시 값-옮기면 변하므로 변경필요X -->
												<span>100</span>
											</div>
											<!-- 슬라이더 바 영역 -->
											<div class="field">
												<!-- 슬라이더 범위 시작 값:0 -->
												<div class="value left">0</div>
												<input type="range" min="0" max="100"
													value="<%=selectedBar%>" step="10" id="distanceBar">
												<!-- 슬라이더 범위 끝나는 값:100 -->
												<div class="value right">100</div>
											</div>
											<!-- 슬라이더 바 영역 끝남 -->
										</div>
										<!-- 반경 범위 설정하는 구역(슬라이더 + 슬라이도 이동버튼) 끝남 -->

									</div>
									<span> 정렬순서 : </span> <label class="check"> <input
										type="radio" name="array" value="time-high-order"> <span
										class="i_check"></span> 소요시간 높은 순
									</label> <label class="check"> <input type="radio" name="array"
										value="time-row-order"> <span class="i_check"></span>
										소요시간 낮은 순
									</label> <label class="check"> <input type="radio" name="array"
										value="distance-high-order"> <span class="i_check"></span>
										전체거리 높은 순
									</label> <label class="check"> <input type="radio" name="array"
										value="distance-row-order"> <span class="i_check"></span>
										전체거리 낮은 순
									</label> <input type="submit" value="Submit">
									<input type="hidden" name="currentlat" id="currentlat">
									<input type="hidden" name="currentlng" id="currentlng">
									<input type="hidden" name="routeList" id="routeList"> 
									<input type="hidden" name="routeForDistance" id="routeForDistance">
									<input type="hidden" name="selectedValue" id="selectedValue">
									<input type="hidden" name="selectedBar" id="selectedBar">
									<input type="hidden" name="address" id="address">
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

						<!-- 지도 옆 Course List 구역(타이틀 + 코스리스트) -->
						<div class="left_content" style="height: calc(100vh - 245px);">
							<!-- 지도 옆 Course List 구역의 타이틀 -->
							<div class="top_title">< COURSE LIST ></div>
							<!-- 지도 옆 Course List 구역의 코스 리스트들 나올 구역 -->
							<div class="list_poi" id="courseList">
								<ul>
									<!--  지도 옆 Course List 구역의 각 코스 구역-> 각 코스는 li에 해당  -->
									<c:choose>
									<c:when test="<%=routelist.size()>=1%>">
									<c:set var="distance" value="<%=distances%>" />
									<c:forEach items="<%=routelist%>" var="course"
										varStatus="status">
										<li id="list_${status.index }" >
											<!-- 왼쪽 여백용 --> <span class="td1"></span> <!-- 가운데 컨텐츠 영역(오른쪽은 좋아요 하트 영역 제외) 감싸고 있음 -->
											<span class="td3"> <!-- 가운데 컨텐츠 영역 --> <span
												class="poi_content" onclick="view(${course.idxNum})"> <!-- 코스 제목 감싸는 영역 --> <span
													class="subject"> <!-- 코스 제목 --> <span
														class="text elp">${course.location }</span>
												</span> <span class="data"> <!-- 코스 정보(전체거리, 소요시간, 난이도) 감싸는 영역 -->
														<span class="text"> <span class="t1">전체 거리</span>
															${course.length } <!-- <전체거리 <(전체거리)값>>형식으로 span으로 감쌈 -->
													</span> <span class="text"> <span class="t1">예상 시간</span>
															${course.expect_time } <!-- <전체거리 <(전체거리)값>>형식으로 span으로 감쌈 -->
													</span> <span class="text"> <span class="t1">난이도</span>
															${course.difficulty } <!-- <전체거리 <(전체거리)값>>형식으로 span으로 감쌈 -->
													</span> <span class="text"> <span class="t1">코스 위치</span>
															 ${course.distance}km 
													</span><span class="text"> <span class="t1">좋아요 수</span>
															 ${course.recommend }
													</span>
													</span> 
												</span>
											</span> <!-- 코스 정보(전체거리, 소요시간, 난이도) 감싸는 영역 끝남-->
										</span> <!--	 코스 가운데 컨텐츠 영역 끝남 --> <!-- 코스 가운데 컨텐츠 감싸는 영역 끝남 --> <!-- 좋아요 누르는 하트 영역 -->
											
											<button type="button" onclick="sendCourse('${course.id}', '${course.location}')">선택</button>
										</li>
									</c:forEach>
									</c:when>
									<c:otherwise>
									<li>
										<span id="SearchNone"> 검색 된 경로가 없어요..</span>
										</li>
									</c:otherwise>
									</c:choose>
									<!--  지도 옆 Course List 구역의 각 코스 구역(-> 각 코스는 li에 해당) 끝남 -->

								</ul>
							</div>
							<!-- 지도 옆 Course List 구역의 코스 리스트들 나올 구역 끝남-->

						</div>
						<!-- 지도 옆 Course List 구역(타이틀 + 코스리스트) 끝남-->

					</div>
					<div class="content">
						<div id="container">
							<div id="rvWrapper">
								<div id="roadview" style="width: 100%; height: 100%;"></div>
								<!-- 로드뷰를 표시할 div 입니다 -->
								<div id="close" title="로드뷰닫기" onclick="closeRoadview()">
									<span class="img"></span>
								</div>
							</div>
							<div id="mapWrapper">
								<div id="map" style="width: 100%; height: 100%"></div>
								<!-- 지도를 표시할 div 입니다 -->
								<div id="roadviewControl" onclick="setRoadviewRoad()"></div>
							</div>
						</div>
					</div>
					<div class="course_nav"></div>

				</div>
			</div>

			<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
			<script type="text/javascript" src=./resources/js/trangle2.js></script>
			<%@ include file="footer.jsp"%>
		</div>
	</div>
</body>

</html>