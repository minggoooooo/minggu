package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;

public class IdCheckServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		int result = dao.checkDuplicateId(id);
		
		req.setAttribute("userId", id);
		req.setAttribute("result", result);
		req.getRequestDispatcher("idCheck.jsp").forward(req, resp);
		dao.close();
		
	}

}