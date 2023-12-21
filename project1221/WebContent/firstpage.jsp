<%@page import="java.util.HashMap"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="controller.GpxParser"%>
<%@page import="java.io.Console"%>
<%@page import="java.io.File"%>
<%@page import="model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CourseDAO"%>
<%@page import="controller.GpxParser"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	CourseDAO dao = new CourseDAO();
// 코스 리스트 받아오기
ArrayList<Course> list = dao.LoadCourse();
// 파일을 넣을 리스트
ArrayList<File> fileList = new ArrayList<>();

// 검색할 디렉토리 경로
String directoryPath = "C:\\paths";
File directory = new File(directoryPath);
//디렉토리에 있는 파일들 리스트 얘들은 정렬순서가 지 맘대로임
File[] files = directory.listFiles();
// 파일이 있는 코스 리스트만 정렬
ArrayList<Course> isFileList = new ArrayList<>();

// 이거는 id 순으로 가져왔음
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
dao.close();
// 파일이 여러개니 한 파일 한 파일 파싱을 위하여 map을 만든뒤 gpxparser라는 자바파일 (gpx를 XML로 파싱)
Map<String, ArrayList<String>> coordinates = GpxParser.parseGpx(fileList);

// 배열을 그대로 JS파일에 넘기고 싶어서 만들었음.
String[] coordinateKeys = coordinates.keySet().toArray(new String[0]);

//이차원 배열이라 조금 복잡해유
String[][] coordinateValues = coordinates.values().stream().map(list2 -> list2.toArray(new String[0]))
		.toArray(String[][]::new);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코스찾기 페이지1</title>
<script type="text/javascript"
	src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=5b706854aedb7b02e16f877971562d29&libraries=services,drawing"></script>
<link rel="stylesheet" href="./resources/css/map3.css">

</head>
<script type="text/javascript">
	var items =
<%=new Gson().toJson(isFileList)%>
	var keys =
<%=new Gson().toJson(coordinateKeys)%>
	;
	var values =
<%=new Gson().toJson(coordinateValues)%>
	;
</script>
<body onload="getCurrentLocation()">
	<div class="distance-slider-container">
		<label for="distanceBar">거리 : (단위 : 10km) </label>
		<h3 id="distanceValue"></h3>
		<input type="range" id="distanceBar" min="10" max="100" step="10"
			value="100"
			style='background-image: url("./resources/file/bar.png");'>
	</div>
	<div id="container">
		<div id="mapWrapper">
			<div id="map" style="width: 100%; height: 100%"></div>
			<!-- 지도를 표시할 div 입니다 -->
			<div id="roadviewControl" onclick="setRoadviewRoad()"></div>
		</div>
	</div>

	<!-- 하단 컨테이너 생성 -> 여기에 리스트 만들고 마커와 연동 할 거임
   		아마 마커 생성할때 low-container에 += <div>marker.tilte...이런정보넣을듯</div>
    -->
	<div id="low_container">
		<div id="courseList"></div>
	</div>
	<form action="LocationServlet" method="post" id="geolocationForm" accept-charset="UTF-8">
		<input type="hidden" name="currentlat" id="currentlat">
		<input type="hidden" name="currentlng" id="currentlng"> <input
			type="hidden" name="routeList" id="routeList"> <input
			type="hidden" name="routeForDistance" id="routeForDistance">
		<button type="submit"></button>
	</form>
	<!-- 예제에서는 버튼 클릭 시 getAddress 함수 호출 -->
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script type="text/javascript" src=./resources/js/firstpage.js></script>
</body>
	<!-- 선택된 주소를 표시할 엘리먼트 -->
</html>