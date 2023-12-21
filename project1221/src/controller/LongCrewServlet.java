package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;
import model.CrewDAO;
import model.CrewDTO;
import model.CrewJoinDAO;
import model.CrewJoinDTO;
import model.CrewRecruitDAO;
import model.CrewRecruitDTO;
import model.CrewScheduleMemberDAO;
import model.CrewScheduleMemberDTO;
import model.LongCrewDAO;
import model.LongCrewMemberDTO;
import model.LongCrewRecruitDTO;
import model.MemberDAO;

public class LongCrewServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
	
		String uri = req.getRequestURI();					// 절대 경로 (현재 파일 이름 포함)
		String Path = req.getContextPath();					// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
	
		if(command.equals("longcrewRecruit.long")) {
			recruit(req, resp);
			return;
		} else if(command.equals("joinCrew.long")) {
			joinCrew(req,resp);
			return;
		} else if(command.equals("registerSchedule.long")) {
			registerSchedule(req, resp);
		}
		
		
		
	}
	
	
	// 장기크루 만들기
	public void recruit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String crewName = req.getParameter("crewName");
		String content = req.getParameter("content");
		int memberNum = Integer.parseInt(req.getParameter("memberNum"));
		String adminId = req.getParameter("adminId");
		
		// 크루 생성 날짜
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String created = formatter.format(now);
		
		LongCrewRecruitDTO dto = new LongCrewRecruitDTO();
		dto.setCrewName(crewName);
		dto.setContent(content);
		dto.setMemberNum(memberNum);
		dto.setCreated(created);
		dto.setAdminId(adminId);
		
		
		// 장기크루 만들기
		LongCrewDAO dao = new LongCrewDAO();
		dao.makeLongCrew(dto);
		
		// 크루장 자동으로 멤버 가입됨
		LongCrewMemberDTO dto2 = new LongCrewMemberDTO();
		dto2.setCrewName(crewName);
		dto2.setMemId(adminId);
		dto2.setMemberNum(memberNum);
		dto2.setAdmin(true);
		dto2.setJoinDate(created);
		dao.joinLongCrew(dto2);
		dao.close();
		
		resp.sendRedirect("longCrewRecruitList.jsp");		// 크루등록하면 모든 장기크루리스트 페이지로 이동
				
		
	}
	
	
	// 장기크루에 가입하기
	public void joinCrew(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		String crewName = req.getParameter("crewName");
		int memberNum = Integer.parseInt(req.getParameter("memberNum"));
		String memId = req.getParameter("userId");
		String adminId = req.getParameter("adminId");
		
		// 성별 가져오기
		MemberDAO dao = new MemberDAO();
		String gender = dao.selectGender(memId);
		
		// 장기크루 승인대기상태(임시가입정보 테이블인 join테이블에 데이터 넣음)
		CrewJoinDTO dto = new CrewJoinDTO();
		dto.setCrewName(crewName);
		dto.setMemberNum(memberNum);
		dto.setMemId(memId);
		dto.setAdminId(adminId);
		dto.setIsCheck(1);
		dto.setIsShortCrew(false);
		dto.setGender(gender);
		CrewJoinDAO dao2 = new CrewJoinDAO();
		dao2.insertJoinInfo(dto);
		dao2.close();
		
		String encodedCrewName = URLEncoder.encode(crewName, "UTF-8");
		JSFunction.alertLocation(resp, "참여신청이 완료되었습니다", "longcrewRecruitDetail.jsp?crewName=" + encodedCrewName);
		
		/*
		 * Date now = new Date(); SimpleDateFormat formatter = new
		 * SimpleDateFormat("yyyy-MM-dd"); String joinDate = formatter.format(now);
		 */
		
		
		
		/*
		 * LongCrewMemberDTO dto = new LongCrewMemberDTO(); dto.setCrewName(crewName);
		 * dto.setMemId(memId); dto.setMemberNum(memberNum); dto.setJoinDate(joinDate);
		 * dto.setAdmin(false);
		 * 
		 * 
		 * LongCrewDAO dao = new LongCrewDAO(); dao.joinLongCrew(dto); dao.close();
		 * 
		 * crewName = URLEncoder.encode(crewName, "UTF-8");
		 * JSFunction.alertLocation(resp, "참여신청이 완료되었습니다",
		 * "crewRecruitDetail.jsp?crewName=" + crewName);
		 */
	 
}
	
	// 장기크루 일정 등록하기
	public void registerSchedule(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		
		String crewName = req.getParameter("crewName");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String location = req.getParameter("location");
		int memberNum = Integer.parseInt(req.getParameter("memberNum"));
		String gatherDate = req.getParameter("gatherDate");
		String adminId = req.getParameter("adminId");
		
		
		// 등록날짜
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String created = formatter.format(date);
		
		// 장기크루 일정 등록
		CrewRecruitDTO dto = new CrewRecruitDTO();
		dto.setCrewName(crewName);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setLocation(location);
		dto.setMemberNum(memberNum);
		dto.setCreated(created);
		dto.setGatherDate(gatherDate);
		dto.setAdminId(adminId);
		LongCrewDAO dao = new LongCrewDAO();
		dao.regiterSchedule(dto);
		
		// 방금 등록한 일정 no 가져오기
		CrewRecruitDAO dao2 = new CrewRecruitDAO();
		int no = dao2.selectNo();
		
		// 크루장 자동으로 일정에 참여됨
		CrewScheduleMemberDTO dto2 = new CrewScheduleMemberDTO();
		dto2.setNo(no);
		dto2.setCrewName(crewName);
		dto2.setMemberNum(memberNum);
		dto2.setMemId(adminId);
		dto2.setMemAdmin(true);
		CrewScheduleMemberDAO dao3 = new CrewScheduleMemberDAO();
		dao3.joinSchedule(dto2);
		dao.close();
		dao2.close();
		dao3.close();
		String encodedCrewName = URLEncoder.encode(crewName, "UTF-8");
		JSFunction.alertLocation(resp, "일정이 등록되었습니다", "plannedSchedule-longcrew.jsp?crewName=" + encodedCrewName);
	}
	
	
	
}
