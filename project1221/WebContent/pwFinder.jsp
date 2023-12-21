<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title></title>

<link href="./resources/css/layout.css" rel="stylesheet">
<link href="./resources/css/finder.css" rel="stylesheet">
</head>
<body>

	<!-- login.jsp 구조 -->
	<div id="root">
		<div class="app">
			<div class="container">
				<!-- header.jsp 시작 -->
				<!-- <div class="banner"></div>
				 <div class="header"></div> -->
				<%@ include file="header.jsp"%>
				<!-- header.jsp 끝 -->

				<div class="mid">
					<div class="mid-inner">
						<div class="login_wrap">
							<div class="login_container">
								<div class="login_cont">
									<!-- 로고 시작 -->
									<div class="logo">
										<a href="/index.html"> <img
											src="./resources/images/img_logo.png" alt="로고" width="100"
											height="100">
										</a>
									</div>
									<!-- 로고 끝 -->
									<h1>비밀번호 찾기</h1>
									<p>나의 정보> 등록된 이름/ 이메일 / 생년월일 정보와 일치해야만<br> 인증번호를 받으실 수 있습니다.</p>

									<form name="form1" method="post" action="FindPwServlet"
										target="loginiframe">
										<input type="hidden" name="mail"> <input type="hidden"
											name="authtext" value=""> <input type="hidden"
											name="authid"> <input type="hidden" name="find_type"
											value="">
										<fieldset>
											<legend>member login</legend>
											<ul class="frm_list">
												<li class="id">
												<span>
														<input type="text" id="id" name="id"
														class="MS_input_txt" value="" maxlength="30" title="아이디"
														placeholder="아이디">
												</span>
												</li>
												<li class="name">
												<span>
														<input type="text" id="name" name="name"
														class="MS_input_txt" value="" maxlength="30" title="이름"
														placeholder="이름">
												</span>
												</li>
												<li class="email">
													<span>
														<input type="text" id="email" name="email" class="MS_input_txt"
														value="" maxlength="80" title="이메일 주소" placeholder="이메일"
														onfocus="document.form1.focus_ok.value='yes'">
													</span>
												</li>
												<li class="birth">
													<div id="wrap-birth">
														<div class="birth-select">
																<label>
																	<select data-select="" name="birthyear" required="required">
																		<option value="">생년</option>
																		<option value="1920">1920</option>
																		<option value="1921">1921</option>
																		<option value="1922">1922</option>
																		<option value="1923">1923</option>
																		<option value="1924">1924</option>
																		<option value="1925">1925</option>
																		<option value="1926">1926</option>
																		<option value="1927">1927</option>
																		<option value="1928">1928</option>
																		<option value="1929">1929</option>
																		<option value="1930">1930</option>
																		<option value="1931">1931</option>
																		<option value="1932">1932</option>
																		<option value="1933">1933</option>
																		<option value="1934">1934</option>
																		<option value="1935">1935</option>
																		<option value="1936">1936</option>
																		<option value="1937">1937</option>
																		<option value="1938">1938</option>
																		<option value="1939">1939</option>
																		<option value="1940">1940</option>
																		<option value="1941">1941</option>
																		<option value="1942">1942</option>
																		<option value="1943">1943</option>
																		<option value="1944">1944</option>
																		<option value="1945">1945</option>
																		<option value="1946">1946</option>
																		<option value="1947">1947</option>
																		<option value="1948">1948</option>
																		<option value="1949">1949</option>
																		<option value="1950">1950</option>
																		<option value="1951">1951</option>
																		<option value="1952">1952</option>
																		<option value="1953">1953</option>
																		<option value="1954">1954</option>
																		<option value="1955">1955</option>
																		<option value="1956">1956</option>
																		<option value="1957">1957</option>
																		<option value="1958">1958</option>
																		<option value="1959">1959</option>
																		<option value="1960">1960</option>
																		<option value="1961">1961</option>
																		<option value="1962">1962</option>
																		<option value="1963">1963</option>
																		<option value="1964">1964</option>
																		<option value="1965">1965</option>
																		<option value="1966">1966</option>
																		<option value="1967">1967</option>
																		<option value="1968">1968</option>
																		<option value="1969">1969</option>
																		<option value="1970">1970</option>
																		<option value="1971">1971</option>
																		<option value="1972">1972</option>
																		<option value="1973">1973</option>
																		<option value="1974">1974</option>
																		<option value="1975">1975</option>
																		<option value="1976">1976</option>
																		<option value="1977">1977</option>
																		<option value="1978">1978</option>
																		<option value="1979">1979</option>
																		<option value="1980">1980</option>
																		<option value="1981">1981</option>
																		<option value="1982">1982</option>
																		<option value="1983">1983</option>
																		<option value="1984">1984</option>
																		<option value="1985">1985</option>
																		<option value="1986">1986</option>
																		<option value="1987">1987</option>
																		<option value="1988">1988</option>
																		<option value="1989">1989</option>
																		<option value="1990">1990</option>
																		<option value="1991">1991</option>
																		<option value="1992">1992</option>
																		<option value="1993">1993</option>
																		<option value="1994">1994</option>
																		<option value="1995">1995</option>
																		<option value="1996">1996</option>
																		<option value="1997">1997</option>
																		<option value="1998">1998</option>
																		<option value="1999">1999</option>
																		<option value="2000">2000</option>
																		<option value="2001">2001</option>
																		<option value="2002">2002</option>
																		<option value="2003">2003</option>
																		<option value="2004">2004</option>
																		<option value="2005">2005</option>
																		<option value="2006">2006</option>
																		<option value="2007">2007</option>
																		<option value="2008">2008</option>
																		<option value="2009">2009</option>
																		<option value="2010">2010</option>
																		<option value="2011">2011</option>
																		<option value="2012">2012</option>
																		<option value="2013">2013</option>
																		<option value="2014">2014</option>
																		<option value="2015">2015</option>
																		<option value="2016">2016</option>
																		<option value="2017">2017</option>
																		<option value="2018">2018</option>
																		<option value="2019">2019</option>
																		<option value="2020">2020</option>
																		<option value="2021">2021</option>
																		<option value="2022">2022</option>
																		<option value="2023">2023</option>
																</select>
															</label>
														</div>
														<div class="birth-select">
															<label>
																<select name="birthmonth" required="required">
																	<option value="">월</option>
																	<option value="01">1</option>
																	<option value="02">2</option>
																	<option value="03">3</option>
																	<option value="04">4</option>
																	<option value="05">5</option>
																	<option value="06">6</option>
																	<option value="07">7</option>
																	<option value="08">8</option>
																	<option value="09">9</option>
																	<option value="10">10</option>
																	<option value="11">11</option>
																	<option value="12">12</option>
																</select>
															</label>
														</div>
														<div class="birth-select">
															<label>
																<select name="birthdate" required="required">
																	<option value="">일</option>
																	<option value="01">1</option>
																	<option value="02">2</option>
																	<option value="03">3</option>
																	<option value="04">4</option>
																	<option value="05">5</option>
																	<option value="06">6</option>
																	<option value="07">7</option>
																	<option value="08">8</option>
																	<option value="09">9</option>
																	<option value="10">10</option>
																	<option value="11">11</option>
																	<option value="12">12</option>
																	<option value="13">13</option>
																	<option value="14">14</option>
																	<option value="15">15</option>
																	<option value="16">16</option>
																	<option value="17">17</option>
																	<option value="18">18</option>
																	<option value="19">19</option>
																	<option value="20">20</option>
																	<option value="21">21</option>
																	<option value="22">22</option>
																	<option value="23">23</option>
																	<option value="24">24</option>
																	<option value="25">25</option>
																	<option value="26">26</option>
																	<option value="27">27</option>
																	<option value="28">28</option>
																	<option value="29">29</option>
																	<option value="30">30</option>
																	<option value="31">31</option>
																</select>
															</label>
														</div>
													</div>
												</li>
											</ul>
										</fieldset>
									
												
									<div class="btn_wrap">
										<button type="submit" class="btn normal login type01">확인</button>
									</div>
									</form>
									<div class="page-move">
												<a href="">아이디 찾기 ></a>
												</div>
									<!-- 배너 시작 -->
									<div class="login_banner">
										<div class="log-banner-txt">
										회원이신가요?<a href=""> 로그인</a> <br> 아직 회원이 아니신가요? <a href="">회원가입</a>
										</div>
									</div>
									<!-- 배너 끝 -->
								</div>
							</div>
							<script type="text/javascript"
								src="/shopimages/akiiikr/template/work/24508/new_password.js?t=202311271116"></script>

							<script type="text/javascript">
								//enter키 기능
								function CheckKey(e) {
									var e = e || window.event;
									key = e.keyCode;
									if (key == 13) {
										send();
									}
								}
								//주민번호 체크
								function checkJumin(kjumin1, kjumin2) {
									var i, sum = 0;
									var str = kjumin1 + kjumin2;
									var jumin1 = kjumin1, jumin2 = kjumin2;
									if (str.length < 13) {
										return false;
									}
									for (i = 0, sum = 0; i < 12; i++) {
										sum += (((i % 8) + 2) * (str.charAt(i) - "0"));
									}
									sum = 11 - (sum % 11);
									sum = sum % 10;
									if (sum == str.charAt(12)) {
										return true;
									}
									// 외국인 등록번호 체크
									if (fgnCheck(str)) {
										return true;
									}
									return false;
								}

								function findLeft(obj) {
									if (!obj)
										return 0;
									return obj.offsetLeft
											+ findLeft(obj.offsetParent);
								}
								function findTop(obj) {
									if (!obj)
										return 0;
									return obj.offsetTop
											+ findTop(obj.offsetParent);
								}

								//지도의 정보 보여주기페이지에 참고하면 좋을듯
								function ssling(temp) {
									if (document
											.getElementById('MSecureiframe') != null) {
										var _x = document.body.clientWidth
												/ 2
												+ (document.documentElement
														&& document.documentElement.scrollLeft || document.body.scrollLeft)
												- 187;
										var _y = document.body.clientHeight
												/ 2
												+ (document.documentElement
														&& document.documentElement.scrollTop || document.body.scrollTop)
												- 51;

										var MSecureingiframe = document
												.getElementById('MSecureingiframe');

										MSecureingiframe.style.left = _x + 'px';
										MSecureingiframe.style.top = _y + 'px';
										MSecureingiframe.style.visibility = temp;
									}
								}

								//데이터 초기화 함수(form을 보낸 후 해당 필드 초기화에 사용)
								function cleardata(id) {
									if (document.getElementById(id) != null) {
										document.getElementById(id).value = '';
									}
								}
								//특정 url로 form을 제출하는 역할
								function submitsend() {
									document.form1.action = '/shop/lostpass.html';
									document.form1.target = "";
									document.form1.submit();
								}

								function find_type(type) {
									if (document.form1.find_type) {
										document.form1.find_type.value = type;
									}
									send();
								}
								function send() {
									var find_type = '';

									if (document.form1.find_type
											&& document.form1.find_type.value.length > 0) {
										find_type = document.form1.find_type.value;
									}

									if (find_type == 'find_pw') {
										var find_pw_type = 'email';
										if (document.form1.find_pw_type) {
											for (var i = 0; i < document.form1.find_pw_type.length; i++) {
												if (document.form1.find_pw_type[i].checked) {
													find_pw_type = document.form1.find_pw_type[i].value;
												}
											}
										}
										if (typeof document.form1.user_id == 'undefined') {
											alert('아이디 입력란이 없습니다. 상점에 문의하세요.');
											return;
										}
										if (document.form1.user_id.value.length == 0) {
											alert('아이디를 입력해주세요.');
											document.form1.user_id.focus();
											return;
										}

										if (find_pw_type == 'email') {
											if (!document.form1.email) {
												alert('아이디와 비번을 전송할 이메일 주소입력란이 없습니다. 상점에 문의하세요.');
												return;
											}
											if (document.form1.email.value.length == 0) {
												alert('이메일을 입력해주세요.');
												document.form1.email.focus();
												return;
											}
										} else if (find_pw_type == 'mobile') {
											if (!document.form1.mobile) {
												alert('휴대폰 번호 주소입력란이 없습니다. 상점에 문의하세요.');
												return;
											}
											if (document.form1.mobile.value.length == 0) {
												alert('휴대폰 번호를 입력해 주세요.');
												document.form1.mobile.focus();
												return;
											}
										}
									} else if (find_type == 'find_id') {
										var find_id_type = 'email';
										if (document.form1.find_id_type) {
											for (var i = 0; i < document.form1.find_id_type.length; i++) {
												if (document.form1.find_id_type[i].checked) {
													find_id_type = document.form1.find_id_type[i].value;
												}
											}
										}
										if (document.form1.name.value.length == 0) {
											alert("이름을 입력해주세요.");
											document.form1.name.focus();
											return;
										}

										if (find_id_type == 'email') {
											if (!document.form1.find_id_email) {
												alert('이메일 주소입력란이 없습니다.');
												return;
											}
											if (document.form1.find_id_email.value.length == 0) {
												alert('이메일을 입력해주세요.');
												document.form1.find_id_email
														.focus();
												return;
											}
										} else if (find_id_type == 'mobile') {
											if (!document.form1.find_id_mobile) {
												alert('휴대폰 번호 주소입력란이 없습니다.');
												return;
											}
											if (document.form1.find_id_mobile.value.length == 0) {
												alert('휴대폰 번호를 입력해 주세요.');
												document.form1.find_id_mobile
														.focus();
												return;
											}
										}
									} else {
										if (document.form1.name.value.length == 0) {
											alert("이름을 입력해주세요.");
											document.form1.name.focus();
											return;
										}
										if (!document.form1.email) {
											alert('아이디와 비번을 전송할 이메일 주소입력란이 없습니다.');
											return;
										}
										if (document.form1.email.value.length == 0) {
											alert('이메일을 입력해주세요.');
											document.form1.email.focus();
											return;
										}
									}

									if (!$('#authtext').val()
											|| $('input[name=authtext]').length == 0) {
										showdiv('visible');
										return;
									}

									document.form1.authtext.value = $(
											'#authtext').val();
									document.form1.authid.value = $('#authid')
											.val();

									document.form1.mail.value = "send";
									submitsend();
								}
							</script>
							<style type="text/css">
							.setPopupStyle {
								height: 100%;
								min-height: 100%;
								overflow: hidden !important;
								touch-action: none;
							}
							</style>

						</div>
					</div>
				</div>



				<!-- footer.jsp 시작 -->
				<!--  <div class="footer"></div> -->
				<!-- footer.jsp 끝 -->
				<%@ include file="footer.jsp"%>
			</div>
		</div>
	</div>

</body>

</html>