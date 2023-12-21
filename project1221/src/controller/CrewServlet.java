package controller;

import java.io.IOException;
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

public class CrewServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String uri = req.getRequestURI();					// 절대 경로 (현재 파일 이름 포함)
		String Path = req.getContextPath();					// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
	
		if(command.equals("crewRecruit.crew")) {
			recruit(req, resp);
			return;
		} else if(command.equals("joinCrew.crew")) {
			join(req, resp);
			return;
		}
		
	}
		
	// 단기크루만들기
	public void recruit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String crewName = req.getParameter("crewName"); 				// 크루명
		String title = req.getParameter("title"); 						// 모집제목
		String content = req.getParameter("content");					// 조건(내용)
		String location = req.getParameter("location");					// 장소
		int memberNum = Integer.parseInt(req.getParameter("memberNum"));// 모집인원
		String gatherDate = req.getParameter("gatherDate");				// 모임날짜
		String adminId = req.getParameter("adminId");	// 방장(크루장)
		String courseId = req.getParameter("selectedId");
		
		System.out.println(courseId);
		
		// 크루 생성날짜
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String created = formatter.format(now);
		
		CrewRecruitDTO dto = new CrewRecruitDTO();
		dto.setCrewName(crewName);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setLocation(location);
		dto.setMemberNum(memberNum);
		dto.setGatherDate(gatherDate);
		dto.setAdminId(adminId);
		dto.setCreated(created);
		dto.setCourseId(courseId);
		
		// 크루 만들기 dao
		CrewRecruitDAO dao = new CrewRecruitDAO();
		dao.makeCrew(dto);
		
		// 크루 no 받아오기
		int no = dao.selectNo();
		
		// 방장은 자동으로 크루에 가입됨
		CrewDTO crew = new CrewDTO();
		crew.setNo(no);
		crew.setCrewName(crewName);
		crew.setMemberNum(memberNum);
		crew.setMemId(adminId);
		crew.setMemAdmin(true);
		new CrewDAO().joinCrew(crew);
		dao.close();
		resp.sendRedirect("crewList-active.jsp");
	}
	
	// 크루 참여하기
	public void join(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		int no = Integer.parseInt(req.getParameter("no"));
		String crewName = req.getParameter("crewName");
		int memberNum = Integer.parseInt(req.getParameter("memberNum"));
		String adminId = req.getParameter("adminId");
		String userId = req.getParameter("userId");
		boolean memAdmin = false;
		
		// 단기크루 승인대기상태(임시가입정보 테이블인 join테이블에 데이터 넣음)
		CrewJoinDTO dto = new CrewJoinDTO();
		dto.setCrewName(crewName);
		dto.setMemId(userId);
		dto.setAdminId(adminId);
		dto.setIsCheck(1);
		dto.setMemberNum(memberNum);
		dto.setIsShortCrew(true);
		CrewJoinDAO dao = new CrewJoinDAO();
		dao.insertJoinInfo(dto);
		dao.close();
		
		String encodedCrewName = URLEncoder.encode(crewName, "UTF-8");
		JSFunction.alertLocation(resp, "참여신청이 완료되었습니다", "crewRecruitDetail.jsp?crewName=" + encodedCrewName);
		
		/*
		 * CrewDTO dto = new CrewDTO(); dto.setNo(no); dto.setCrewName(crewName);
		 * dto.setMemberNum(memberNum); dto.setMemId(userId); dto.setMemAdmin(false);
		 * 
		 * CrewDAO dao = new CrewDAO(); dao.joinCrew(dto); dao.close(); String
		 * encodedCrewName = URLEncoder.encode(crewName, "UTF-8");
		 * JSFunction.alertLocation(resp, "참여신청이 완료되었습니다",
		 * "crewRecruitDetail.jsp?crewName=" + crewName);
		 */
	}
	
}