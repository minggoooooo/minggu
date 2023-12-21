<%@page import="model.RecommendDAO"%>
<%@page import="model.LongCrewMemberDTO"%>
<%@page import="model.LongCrewDAO"%>
<%@page import="model.ReplyDAO"%>
<%@page import="model.ReplyDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.BoardDAO"%>
<%@page import="model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="loginCheck.jsp"%>
<%
	String UserId = (String) session.getAttribute("userId");
	int no = Integer.parseInt(request.getParameter("no")); // 게시글 넘버
	BoardDTO board = new BoardDAO().selectBoard(no); // 특정 게시물 불러오기
	RecommendDAO dao3 = new RecommendDAO();
	board.setRecommend(dao3.SelectRecommendCount(no));
	pageContext.setAttribute("board", board);
	String writerId = board.getId(); // 게시물 작성자
	pageContext.setAttribute("writerId", writerId);
	List<ReplyDTO> replyList = new ReplyDAO().selectReplyByNo(no);
	String crewName = board.getCrewName();
	LongCrewDAO dao = new LongCrewDAO();
	String adminId = dao.selectAdminId(crewName); // 크루 방장 id
	pageContext.setAttribute("adminId", adminId);
	List<LongCrewMemberDTO> memberList = dao.selectMemid(crewName); // 특정 장기크루의 모든 memId + adminId 가져오기
	
	boolean boo = false;
	boolean checkRecommend = dao3.checkRecommend(no, UserId);
	for (LongCrewMemberDTO mem : memberList) {
	   if (mem.getMemId().equals(UserId)) {
	      boo = true;
	   }
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>크루 게시판 글 상세보기</title>
<link href="./resources/css/layout.css" rel="stylesheet">
<link href="./resources/css/CrewBoardPage.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="./resources/js/recommend.js"></script>
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
                        <h2 class="tit-page">게시판 상세 보기</h2>

                        <div class="page-body">

                           <div class="info">
                              <div class="info-H">

                                 <div class="wrap-gesi-btn">
                                    <!-- 사용자가 작성자와 같거나 방장이면 수정, 삭제 버튼 보이게 설정하기 -->
                                    <c:if test="${board.id eq UserId || UserId eq adminId}">
                                       <a
                                          href="DeleteBoardServlet?no=${board.no}&writerId=${board.id}&userId=${UserId}&crewName=${board.crewName}"><div
                                             class="gesi-btn edit">수정</div></a>
                                       <a href="#?no=${board.no}"><div class="gesi-btn del">삭제</div></a>
                                    </c:if>
                                 </div>
                              </div>


                              <div class="order">
                                 <dl>
                                    <div id="profile_i"></div>
                                    <!-- profile -->
                                    <dt></dt>
                                    <dd class="ico3">
                                       <c:if test="${board.category eq 'notice'}">
                                          <div class="cate">* 공지사항 *</div>
                                       </c:if>
                                       <c:if test="${board.category eq 'board'}">
                                          <div class="cate">* 자유게시판 *</div>
                                       </c:if>
                                       <c:if test="${board.category eq 'greeting'}">
                                          <div class="cate">* 가입인사 *</div>
                                       </c:if>
                                       <!-- ${board.category} -->
                                       <strong>크루 활동 시 필수 숙지사항</strong>
                                    </dd>
                                 </dl>
                                 <dl>
                                    <dt>크루명</dt>
                                    <dd class="ico3">
                                       <strong>${board.crewName}</strong>
                                    </dd>
                                 </dl>
                                 <dl>
                                    <dt>작성자 ID</dt>
                                    <dd class="ico3">
                                       <strong>${board.id}<span>(${board.nickname})</span></strong>
                                    </dd>
                                 </dl>
                              </div>


                           </div>
                        </div>

                        <div class="info_box">
                           <div class="content-area">
                              <!-- ${board.content} -->
                              <div class="es_section_area">
                                 <h4 class="wc_section_title wd_mt_0">* 크루활동 참고사항 *</h4>
                                 <div class="bs_info_box">
                                    <div align="center">
                                       <br> <span
                                          style="font-size: 14pt; background-color: rgb(51, 51, 204); color: rgb(255, 255, 255);">&nbsp;클래스
                                          지침사항&nbsp;</span>
                                    </div>
                                    <br>

                                    <div align="center">
                                       <span style="font-size: 12pt;">${board.content}&nbsp;</span>
                                    </div>

                                    <c:forEach items="<%=memberList%>" var="member">
                                       <c:if test="${userId eq member.memId}">
                                       <c:set var="check" value="<%=checkRecommend %>"/>
                                       <c:choose>
                                          <c:when test="${check }">
                                             <a href="#"
                                                onclick="updateCount(${board.no},'${board.id }'); return false;">
                                                <span class="t_block"> <!-- 좋아요 하트 아이콘 --> <span
                                                   class="i_alike"></span> <!-- 좋아요 갯수 값 --> <span
                                                   class="text" id="board">${board.recommend }</span>
                                             </span>
                                             </a>
                                          </c:when>
                                          <c:otherwise>
                                             <a href="#"
                                                onclick="updateCount(${board.no},'${board.id }'); return false;">
                                                <span class="t_block"> <!-- 좋아요 하트 아이콘 --> <span
                                                   class="i_like"></span> <!-- 좋아요 갯수 값 --> <span
                                                   class="text" id="board">${board.recommend }</span>
                                             </span>
                                             </a>
                                          </c:otherwise>
                                       </c:choose>
                                       
                                       </c:if>
                                    </c:forEach>
                                    <c:set var="bool" value="<%=boo %>"></c:set>
                                       <c:if test="${!bool}">
                                       <a href="#"
                                             onclick="noneMem(); return false;">
                                             <span class="t_block"> <!-- 좋아요 하트 아이콘 --> <span
                                                class="i_like"></span> <!-- 좋아요 갯수 값 --> <span
                                                class="text" id="board">${board.recommend }</span>
                                          </span>
                                       </a>
                                       </c:if>
                                 </div>
                              </div>

                           </div>
                           <div class="content-area">
                              <!-- 이미지파일 출력 영역 -->
                              <!-- forEach문 시작할 구역 -->
                              <div class="main-con3">
                                 <div class="main-con-title">
                                    <h2>사진</h2>
                                 </div>
                                 <ul class="main-con-event">
                                    <!-- 이미지는 있으면 화면에 출력, 없으면 기본이미지-->
                                    <c:if test="${not empty board.imgName}">
                                       <li><span class="img" aria-hidden="true"> <img
                                             src="./resources/images/${board.imgName}"
                                             alt="${board.imgName}" alt=""></span></li>
                                    </c:if>
                                    <c:if test="${empty board.imgName}">
                                       <li><span class="img" aria-hidden="true"> <img
                                             src="./resources/images/dog.png" alt="강아지사진" alt=""></span></li>
                                    </c:if>
                                    <!-- <li>
                                             <span class="img" aria-hidden="true"><img
                                                src="//static11.samsungcard.com/wcms/direct_insurance/images/main/new2/common/01_traveler_510x336.png"
                                                alt=""></span>
                                       </li> -->
                                    <%-- </c:if> --%>
                                 </ul>

                              </div>

                           </div>
                           <div class="content-area"></div>
                           <div class="content-area">
                              <div class="main-con2 act">
                                 <div class="main-con-title">
                                    <h2>댓글</h2>
                                 </div>
                                 <ul>
                                    <li>
                                       <!-- 댓글 쓰기 창 --> <!-- 크루의 멤버가 맞으면 댓글쓰기창 보임 --> <c:if
                                          test="<%=boo%>">
                                          <form action="./registerReplyLong.reply" method="post"
                                             class="comment-box_frm">
                                             <input type="hidden" name="id" value="<%=UserId%>" /> <input
                                                type="hidden" name="b_no" value="<%=no%>" />
                                             <textarea name="content" rows="3" cols=""
                                                placeholder="댓글을 입력해 주세요" class="comment-box"></textarea>
                                             <input type="submit" value="등록" class="comment-box_btn">
                                          </form>
                                          <!-- 댓글 쓰기 창 마감 -->
                                       </c:if> <!-- 댓글 리스트 마감 -->

                                    </li>
                                 </ul>

                                 <!--댓글 리스트 시작할 구역 -->
                                 <div class="wc_qna_wrap">
                                    <!-- 댓글 출력 -->
                                    <c:forEach items="<%=replyList%>" var="reply">

                                       <div class="ec_list_area">
                                          <div class="bc_profile_box">
                                             <span class="ic_image_field"><img
                                                src="https://moram.kr/data/member_image/ka/kakao_806307e2.gif"
                                                alt="프로필 이미지"></span>
                                             <div class="ic_info_field">
                                                <strong class="sc_name">${reply.id}</strong>
                                                <div class="wc_score_star_wrap">
                                                   <span class="ec_label">${reply.created}</span>
                                                </div>
                                             </div>
                                          </div>
                                          <div class="bc_comment_box">${reply.content}</div>
                                       </div>
                                    </c:forEach>



                                 </div>
                              </div>

                           </div>
                           <!-- info-box -->
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
   </div>
</body>
</html>