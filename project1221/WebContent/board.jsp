<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/board.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>board</title>
<script src="resources/js/board.js"></script>
</head>
<body>

	<div class="container">
		<%@include file="header.jsp"%>
		<div class="mid">
			<div class="container-background">
				<div class="mid-inner">
					<div class="mid-head">
						<input type="text" id="location_bar" placeholder="주소를 설정하세요">
						<div id="popup" style="display: none;">
							<div class="addressList">
								<div class="address-cate">
									<div class="address-menu">
										<button type="button" id="1">서울특별시</button>
										<button type="button" id="2">부산광역시</button>
										<button type="button" id="3">대구광역시</button>
										<button type="button" id="4">인천광역시</button>
										<button type="button" id="5">광주광역시</button>
										<button type="button" id="6">대전광역시</button>
										<button type="button" id="7">울산광역시</button>
										<button type="button" id="8">세종특별자치시</button>
										<button type="button" id="9">경기도</button>
										<button type="button" id="10">충청북도</button>
										<button type="button" id="11">충청남도</button>
										<button type="button" id="12">전라북도</button>
										<button type="button" id="13">전라남도</button>
										<button type="button" id="14">경상북도</button>
										<button type="button" id="15">경상남도</button>
										<button type="button" id="16">제주특별자치도</button>
										<button type="button" id="17">강원특별자치도</button>
									</div>
									<div class="address-tab">
										<div id="1">
											<button type="button">강남구</button>
											<button type="button">강동구</button>
											<button type="button">강북구</button>
											<button type="button">강서구</button>
											<button type="button">관악구</button>
											<button type="button">광진구</button>
											<button type="button">구로구</button>
											<button type="button">노원구</button>
											<button type="button">도봉구</button>
											<button type="button">동대문구</button>
											<button type="button">동작구</button>
											<button type="button">마포구</button>
											<button type="button">서대문구</button>
											<button type="button">서초구</button>
											<button type="button">성동구</button>
											<button type="button">성북구</button>
											<button type="button">송파구</button>
											<button type="button">양천구</button>
											<button type="button">영등포구</button>
											<button type="button">용산구</button>
											<button type="button">은평구</button>
											<button type="button">종로구</button>
											<button type="button">중구</button>
											<button type="button">중랑구</button>
										</div>
										<div id="2">
											<button type="button">강서구</button>
											<button type="button">금정구</button>
											<button type="button">기장군</button>
											<button type="button">남구</button>
											<button type="button">동구</button>
											<button type="button">동래구</button>
											<button type="button">부산진구</button>
											<button type="button">북구</button>
											<button type="button">사상구</button>
											<button type="button">사하구</button>
											<button type="button">서구</button>
											<button type="button">수영구</button>
											<button type="button">연제구</button>
											<button type="button">영도구</button>
											<button type="button">중구</button>
											<button type="button">해운대구</button>
										</div>
									</div>
								</div>
								<div class="btn-wrap">
									<a class="address-setting" href="#"><span>설정</span></a>
								</div>
							</div>
						</div>
					</div>
					<div class="crewboard">
						<div class="crew-sort">
							<input type="button" class="crew-sort" id="oneday" value="원데이 크루">
							<input type="button" class="crew-sort" id="longday"
								value="롱데이 크루">
						</div>
						<select>
							<option value="latest" selected>최신순</option>
							<option value="popular">인기순</option>
							<option value="date">날짜순</option>
						</select>
					</div>
					<div class="crew-card">
						<div class="card">
							<img class="card-img" src="./resources/images/card1.jpg" alt="">
							<div class="card-title">제목1</div>
							<div class="card-contents">내용1</div>
						</div>
						<div class="card">
							<img class="card-img" src="./resources/images/card2.jpg" alt="">
							<div class="card-title">제목2</div>
							<div class="card-contents">내용2</div>
						</div>
						<div class="card">
							<img class="card-img" src="./resources/images/card3.jpg" alt="">
							<div class="card-title">제목3</div>
							<div class="card-contents">내용3</div>
						</div>
						<div class="card">
							<img class="card-img" src="./resources/images/card4.jpg" alt="">
							<div class="card-title">제목4</div>
							<div class="card-contents">내용4</div>
						</div>
						<div class="card">
							<img class="card-img" src="./resources/images/winter.png" alt="">
							<div class="card-title">제목5</div>
							<div class="card-contents">내용5</div>
						</div>
						<div class="card">
							<img class="card-img" src="./resources/images/chrismas.jpg"
								alt="">
							<div class="card-title">제목6</div>
							<div class="card-contents">내용6</div>
						</div>
						<div class="card">
							<img class="card-img" src="./resources/images/running.jpg" alt="">
							<div class="card-title">제목7</div>
							<div class="card-contents">내용7</div>
						</div>
						<div class="card">
							<img class="card-img" src="./resources/images/chrismas2.jpeg"
								alt="">
							<div class="card-title">제목8</div>
							<div class="card-contents">내용8</div>
						</div>
						<div id="pagination">
							<a href="#" class="prev">이전</a> <a href="#" class="page active">1</a>
							<a href="#" class="page">2</a> <a href="#" class="page">3</a> <a
								href="#" class="next">다음</a>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>

</body>
</html>