<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="resources/css/scheduleregister.css" rel="stylesheet" type="text/css">
<%@include file="header.jsp"%>
<%
	String crewName = request.getParameter("crewName");

%>
<title>장기 크루 일정 등록 페이지</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
      $(document).ready(function() {
          $("#selectButton").click(function() {
              // 새 창 열기
              var newWindow = window.open("firstpage2.jsp", "_blank");

          });

          // 선택 정보 받기
          window.addEventListener("message", function(event) {
              if (event.data) {
                  // 선택된 데이터를 특정 위치에 출력
                  $("#selectedId").val(event.data.id);
                   $("#selectedName").val(event.data.name);
              }
          });
      });
  </script>
</head>

<div class="mid">
	<div class="container-background">
		<div class="schedule-write">
			<div class="write-header">일정 등록</div>
			<form action="./registerSchedule.long" method="post" name="frm">
				<label for="date">모임날짜</label>
				
				<input name="gatherDate" type="date" required oninvalid="this.setCustomValidity(' 달력을 클릭하세요')" data-placeholder="달력을 클릭하세요">
				
				
				
				<!-- 실제로는 코스선택 클릭 시 명우씨가 만든 페이지로 이동해서 코스 선택함 -->
		
				<input type="hidden" id="selectedId">
		        <label for="selectedId">코스</label>
		       	<div class="course-choice">
		        <input type="text" id="selectedName" name="courseId" placeholder="코스 선택 버튼을 클릭하세요" readonly >
		        <button type="button" id="selectButton">코스 선택</button>
				</div>		        
				
				
				<label for="attendees" >제목</label>
				<input type="text" id="attendees" name="title" placeholder=" EX  :   1명">
				
				<label for="attendees" >모집 인원</label>
				<input type="text" id="attendees" name="memberNum" placeholder=" EX  :   1명">
				
				<label for="fee">조건(내용)</label>
				<input type="text" id="fee" name="content" placeholder=" EX  :   1만원">
				
				<label for="fee">장소</label>
				<input type="text" id="fee" name="location" placeholder=" EX  :   1만원">
				
				<input type="hidden" name="crewName" value="<%=crewName%>">
				<!-- 장기크루 일정 등록할 때는 adminId가 작성자 아이디임 -->
				<input type="hidden" name="adminId" value="${UserId}"> 
				<input type="submit" value="등    록">
			</form>
		</div>
	</div>
</div>
<%@include file="footer.jsp"%>
</html>