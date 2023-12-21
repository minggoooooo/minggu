package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JSFunction;
import model.CrewScheduleMemberDAO;
import model.CrewScheduleMemberDTO;

public class JoinScheduleServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		int no = Integer.parseInt(req.getParameter("no"));
		String crewName = req.getParameter("crewName");
		int memberNum = Integer.parseInt(req.getParameter("memberNum"));
		String memId = (String)req.getSession().getAttribute("userId");
		
		CrewScheduleMemberDTO dto = new CrewScheduleMemberDTO();
		dto.setNo(no);
		dto.setCrewName(crewName);
		dto.setMemberNum(memberNum);
		dto.setMemId(memId);
		dto.setMemAdmin(false);
		
		CrewScheduleMemberDAO dao = new CrewScheduleMemberDAO();
		int result = dao.joinSchedule(dto);
		dao.close();
		
		if(result == 1) {
			JSFunction.alertBack(resp, "일정에 참여완료되었습니다");
		} else {
			JSFunction.alertBack(resp, "다시 시도해 주세요");
		}
		
	}
	
	
}
