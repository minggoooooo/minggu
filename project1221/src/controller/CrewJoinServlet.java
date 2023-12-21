package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.JSFunction;
import model.CrewJoinDAO;
import model.CrewJoinDTO;
import model.CrewScheduleMemberDAO;
import model.CrewScheduleMemberDTO;
import model.LongCrewDAO;
import model.LongCrewMemberDTO;

public class CrewJoinServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
	
		String uri = req.getRequestURI();					// 절대 경로 (현재 파일 이름 포함)
		String Path = req.getContextPath();					// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
	
		
		if(command.equals("joinCrew.join")) {
			joinCrew(req, resp);
			return;
		}
		
	}
	
	
	public void joinCrew(HttpServletRequest req, HttpServletResponse resp) {
		
		boolean isPermit = Boolean.parseBoolean(req.getParameter("isPermit"));
		String crewName = req.getParameter("crewName");
		String adminId = req.getParameter("adminId");
		String memId = req.getParameter("memId");
		int memberNum = Integer.parseInt(req.getParameter("memberNum"));
		boolean isShortCrew = Boolean.parseBoolean(req.getParameter("isShortCrew"));
		
		System.out.println("크루명 : " + crewName);
		System.out.println("멤버 아이디 : " + memId);
		System.out.println("멤버수 : " + memberNum);
		System.out.println("단기크루여부 : " + isShortCrew);
		
		if(isPermit) {			// 승낙 버튼 눌렀을 경우 
			
			if(isShortCrew == true) {		// 단기크루에 가입할 경우
				
				CrewScheduleMemberDAO dao = new CrewScheduleMemberDAO();
				CrewScheduleMemberDTO dto = new CrewScheduleMemberDTO();

				// 멤버 추가하려는 crewName의 넘버 가져와서 단기크루에 멤버 추가
				int crewNum = dao.selectCrewNum(crewName);
				dto.setNo(crewNum);
				dto.setCrewName(crewName);
				dto.setMemberNum(memberNum);
				dto.setMemId(memId);
				dto.setMemAdmin(false);
				dao.joinSchedule(dto);
				dao.close();
				
				// 멤버 추가후 join 테이블에서 승인완료 상태로 바꿔줌
				CrewJoinDAO dao2 = new CrewJoinDAO();
				dao2.updateJoinInfo2(crewName, memId);
				dao2.close();
				
				// 멤버 추가 후 승인현황으로 이동
				JSFunction.alertLocation(resp, "멤버 승인이 완료되었습니다", "MyPage-sub4.jsp");
				
				
			} else {						// 장기크루에 가입할 경우
				
				LongCrewDAO dao = new LongCrewDAO();
				LongCrewMemberDTO dto = new LongCrewMemberDTO();
				dto.setCrewName(crewName);
				dto.setMemId(memId);
				dto.setMemberNum(memberNum);
				dto.setAdmin(false);
				
				
				
				Date today = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String joinDate = formatter.format(today);
				dto.setJoinDate(joinDate);
				
				dao.joinLongCrew(dto);
				dao.close();
				
				// 멤버 추가후 join 테이블에서 승인완료 상태로 바꿔줌
				CrewJoinDAO dao2 = new CrewJoinDAO();
				dao2.updateJoinInfo2(crewName, memId);
				dao2.close();
				
				// 멤버 추가 후 승인현황으로 이동
				JSFunction.alertLocation(resp, "멤버 승인이 완료되었습니다", "MyPage-sub4-longCrew.jsp");
			
			} 
			
		} else {		// 거절 버튼 눌렀을 경우
			
			if(isShortCrew == true) {			// 단기크루 승인 거절 됐을 경우
				CrewJoinDAO dao = new CrewJoinDAO();
				dao.updateJoinInfo(crewName, memId);		// join 테이블에서 isCheck 3으로 변경(승인거절 상태로 변경)
				dao.close();
				JSFunction.alertLocation(resp, "승인거절 되었습니다", "MyPage-sub4.jsp");
			
			} else {							// 장기크루 승인 거절 됐을 경우
				CrewJoinDAO dao = new CrewJoinDAO();
				dao.updateJoinInfo(crewName, memId);
				dao.close();
				JSFunction.alertLocation(resp, "승인거절 되었습니다", "MyPage-sub4-longCrew.jsp");

			}
		

			
		}
		
	}
	
	
	
	
	
	
}
