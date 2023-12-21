<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="resources/css/layout.css" rel="stylesheet" type="text/css">
<link href="resources/css/introduce.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>홈</title>
</head>
<body>
	<main>
		<div class="container">
		<%@include file="header.jsp"%>
			<div class="mid">
				<div class="mid-inner">
					<!-- 1번쨰 - 시작 -->
					<section>
						<div class="contents">
							<h2>산책</h2>
							<p>
								혼자서도 가까운 이웃과도 <br> 내 주변에서 시작해요.
							</p>
							<p>
								열정과 노력이 깃든 시간은 <br> 건강한 몸을 만드는 자산입니다.
							</p>
						</div>
						<div class="image">
							<img src="resources/images/Walk.png" alt="이미지 파일을 넣어주세요.">
						</div>
					</section>
					<!-- 1번쨰 - 끝 -->
					<section>
						<div>
							<img src="resources/images/bicycle.png" alt="이미지 파일을 넣어주세요.">
						</div>
						<div class="contents">
							<h2>코스 찾기</h2>
							<p>
								거리, 이동 수단에 따라서 <br> 내가 원하는 코스 선택하여 <br> 즐겁게 시작해요.
							</p>
						</div>
					</section>
					<section>
						<div class="contents">
							<h2>크루 활동</h2>
							<p>다른 사람과 함께 참여하여 <br> 혼자서 느낄 수 없었던 경험을 <br> 누려보세요.</p>
						</div>
						<div>
							<img src="resources/images/forest.jpg" alt="이미지 파일을 넣어주세요">
						</div>
					</section>
					<section>
						<div>
							<img src="resources/images/badge.jpg" alt="이미지 파일을 넣어주세요">
						</div>
						<div class="contents">
							<h2>배지</h2>
							<p>목표을 달성하면 <br> 배지를 받을 수 있어요.</p>
             				<p>수집한 배지들을 <br> 이용하여 나를 뽐내봐요.</p>
						</div>
					</section>
					<section>
						<div class="contents">
							<h2>기록을 한눈에!</h2>
							<p>
								기록을 통해 <br> 자신의 성장 발판으로 삼으세요.
							</p>
						</div>
						<div>
							<img src="resources/images/graph.jpg" alt="이미지 파일을 넣어주세요">
						</div>
					</section>

				</div>
			</div>
				<%@include file="footer.jsp"%>
			<!-- <footer class="bottom">
				<div class="bottom-inner">
					<div class="footer-top">
						<a class="footer-logo"></a>
						<ul class="footer-menu">
							<li>이용약관</li>
							<li>개인정보방침</li>
							<li>공지사항</li>
							<li>광고 및 제휴 문의</li>
						</ul>
						<ul class="footer-sns">
						</ul>
					</div>
					<div class="footer-bottom">
						<div class="footer-txt">
							<p>광주광역시 북구 용봉동 국제직업전문학교</p>
							<p>
								<span>T. 010-0000-0000</span> <span>E. naver@naver.com</span>
							</p>
						</div>
					</div>
				</div>
			</footer> -->

		</div>
	</main>
</body>

</html>