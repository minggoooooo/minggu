<%@page import="model.MemberDAO"%>
<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="loginCheck.jsp" %>
<%
	MemberDTO dto = (MemberDTO)session.getAttribute("memberDTO");
	String UserId = dto.getId();
	MemberDTO memberDTO = new MemberDAO().getMemberById(UserId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/layout.css" rel="stylesheet">
<link href="resources/css/edit_MemINFO.css" rel="stylesheet">
</head>
<body>

	<div id="root">
		<div class="app">
			<div class="container">
				<!-- header.jsp 시작 -->
				<%@ include file="header.jsp"%>
				<!-- header.jsp 끝 -->

				<div id="mm_body" class="mm_page">
					<!-- 본문 감싸기 -->
					<div class="mm_page-content">

						<!-- 본문 영역 -->
						<div id="regist_form" class="mm_inner">
						<!-- '회원가입' 제목 영역  -->
							<h3 class="mm_title">
								<b>회원정보 변경</b>
							</h3>
							<!-- '회원가입' 제목 아래 텍스트 영역 -->
							<div class="layer"></div>
							<!-- form 감싸는 영역 -->
							<div class="m_join">
								<!-- form 시작 -->
								<form method="post" name="frm" action="UpdateMemberServlet"  enctype="multipart/form-data"
								 onsubmit="return pwCheck()">
									<!-- 여백 설정위해 집어 넣음 -->
									<h6 class="mm_text-label"></h6>
									<h6 class="mm_text-label"></h6>
									<!--// 여백 설정위해 집어 넣음 -->
									
									<!-- input영역 전 '아이디' 텍스트 영역 -->
									<div class="wrap">
										<h6 class="mm_text-label">
											<b>아이디</b>
										</h6>
										<!-- '아이디' input 영역 --> 
										<!-- label태그로 input박스 안의 텍스트(위치, 정렬 등)요소가 설정되어 있음 -->
										<!-- i태그로 input 박스의 테두리가 설정되어 있음 -->
										<div class="mm_form-text">
											<label id="RO-01"><input type="text" name="id"
												id="input_id" data-text="" maxlength="12" class="textfield"
												value="<%=memberDTO.getId()%>" readonly> <i class="bg_text"></i></label>
										</div>
									</div>
																		
									<!-- input영역 전 '비밀번호' 텍스트 영역 -->
									<div class="wrap">
										<h6 class="mm_text-label">
											<b>변경 비밀번호</b>
										</h6>
										<!-- '비밀번호' input 영역 --> 
										<div class="mm_form-text">
											<label><input type="password" name="pw" id="pw"  value="<%=memberDTO.getPassword()%>" data-text=""
												maxlength="16" class="textfield" placeholder="변경하실 비밀번호를 입력해주세요"><i
												class="bg_text"></i> </label>
										</div>
									</div>
									<div class="mm_note">
											<h6 class="mm_text-label">
												<b></b>
											</h6>
											<div class="">
												<p> 8자 이상 ~ 16자 이하로 입력하세요</p>
												<p>영문, 숫자, 특수문자 사용 가능합니다</p>
											</div>
										</div>
									<div class="wrap-P2">
										<!-- input영역 전 '비밀번호 재입력' 텍스트 영역 -->
										<h6 class="mm_text-label">
											<b>변경 비밀번호 재입력</b>
										</h6>
										<!-- '비밀번호 재입력' input 영역 --> 
										<div class="mm_form-text">
											<label><input type="password" name="pwCheck" id="pwCheck" value="<%=memberDTO.getPassword()%>"  data-text=""
												maxlength="16" class="textfield"
												placeholder="변경하실 비밀번호를 한번 더 입력해주세요"><i class="bg_text"></i>
											</label>
										</div>
										</div>
										<!-- '비밀번호 재입력' input 영역 아래 비밀번호 참고사항 --> 
										
									<div class="wrap">
										<h6 class="mm_text-label">
											<b>이메일 주소</b>
										</h6>
										<div class="mm_formmix-linked">
											<div class="mm_form-text">
	
												<input type="hidden" name="originalEmail" value="<%=memberDTO.getEmail()%>">
												<label><input type="text" name="email1" id="input_email"
													data-text="" maxlength="50" class="textfield"
													placeholder="이메일을 입력해주세요"><i class="bg_text"></i> </label>
											</div>
											<div class="bg-text-email-2">@</div>
											<div class="mm_form-text">
	
												<label><input type="text" name="email2" id="input_email"
													data-text="" maxlength="50" class="textfield"
													placeholder="도메인 입력"><i class="bg_text"></i> </label>
											</div>
										</div>
									</div>
									<div class="wrap-E2">
										<h6 class="mm_text-label">
											<b></b>
										</h6>
										<div class="mm_form-select">
											<label id="bg_text-email-3"><select name="emailList" data-select="" onchange="email_check()">
												<option id="write">직접입력</option>
												<option value="daum.net">daum.net</option>
												<option value="naver.com">naver.com</option>
												<option value="nate.com">nate.com</option>
												<option value="hanmail.net">hanmail.net</option>
												<option value="gmail.com">gmail.com</option></select> </label>
										</div>
									</div>
									<div class="wrap">
										<h6 class="mm_text-label">
											<b>이름</b>
										</h6>
										<div class="mm_form-text">
											<label id="RO-02"><input type="text" value="<%=memberDTO.getName()%>" data-text=""
											 maxlength="20"
												class="textfield" readonly><i
												class="bg_text"></i> </label>
										</div>
									</div>
									<div class="wrap">
										<h6 class="mm_text-label">
											<b>휴대폰 번호</b>
										</h6>
										<div class="mm_formmix-linked">
											<div class="mm_form-text">
												<label><input type="text" name="phone" value="<%=memberDTO.getPhone()%>" id="input_phone"
													data-text="" maxlength="14" class="textfield"
													placeholder="휴대폰 번호를 입력해주세요(ex.010-1234-5678)"><i
													class="bg_text"></i> </label>
											</div>
										</div>
									</div>
									<div class="wrap">
										<h6 class="mm_text-label">
											<b>생년월일</b>
										</h6>
										<div class="mm_formmix-linked">
											<div class="mm_form-select">
											<input type="hidden" name="originalBirthday" value="<%=memberDTO.getBirthDate()%>"/>
												<label><select data-select="" name="birthyear">
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
												</select></label>
											</div>
											<div class="mm_form-select">
												<label><select data-select="" name="birthmonth">
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
												</select> </label>
											</div>
											<div class="mm_form-select">
												<label><select data-select="" name="birthdate">
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
												</select></label>
											</div>
										</div>
									</div>
									<div class="wrap">
										<h6 class="mm_text-label">
											<b>닉네임</b>
										</h6>
										<div class="mm_form-text">
											<label><input type="text" name="nickname" value="<%=memberDTO.getNickname()%>" data-text=""
											 maxlength="20" class="textfield" placeholder="닉네임을 입력해주세요"><i
												class="bg_text"></i> </label>
										</div>
									</div>
									<div class="wrap">
										<h6 class="mm_text-label">
											<b>프로필 사진</b>
										</h6>
										<div class="mm_form-text">
											<input type="hidden" name="originalImg" value="<%=memberDTO.getProfileImg()%>">
											<label><input type="file"  name="newImg" data-text="" maxlength="20"
												class="textfield" placeholder="프로필 사진을 등록해주세요"><i
												class="bg_text"></i> </label>
										</div>
									</div>
									<div class="wrap-gender">
										<h6 class="mm_text-label">
											<b>성별</b>
										</h6>
										<p></p>
										<div class="mm_form-text-2">
											<p class="gender">
											<input type="hidden" name="originalGender" value="<%=memberDTO.getGender()%>"/>
												&nbsp;<label class="label-gender"><input
													type="radio" name="sex" value="남"
													class="input-radio MS_radio"> 남</label> &nbsp;<label
													class="label-gender"><input type="radio" name="sex"
													value="여" class="input-radio MS_radio"> 여</label>
											</p>
										</div>
									</div>

									<div class="mm_foot">
										<div class="mm_btnbox">
											<div class="mm_block">
												<input type="submit" value="변경하기" class="mm_btn __btn_lg_primary__">
												<!-- <button type="button" >
													<b>수정하기</b>
												</button> -->
											</div>
										</div>
									</div>
									<input hidden="hidden">
								</form>

							</div>
						</div>
					</div>
				</div>
				<%@ include file="footer.jsp"%>
			</div>
		</div>
	</div>


<script type="text/javascript" src="./resources/js/register.js"></script>
<script type="text/javascript" src="./resources/js/pwCheck2.js"></script>
</body>
</html>