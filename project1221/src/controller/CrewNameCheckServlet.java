package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CrewRecruitDAO;

public class CrewNameCheckServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
	
		String crewName = req.getParameter("crewName");
		CrewRecruitDAO dao = new CrewRecruitDAO();
		int result = dao.checkCrewName(crewName);
		
		req.setAttribute("result", result);
		req.setAttribute("msg", "이미 존재하는 크루명입니다");
		req.getRequestDispatcher("crewRecruit.jsp").forward(req, resp);
		dao.close();
	}
	
}