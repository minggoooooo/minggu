package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;
import model.MemberDAO;

public class FindIdServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String name = req.getParameter("name");
		String birthDate = req.getParameter("birthDate");
		String email = req.getParameter("email");
		
		// 생년월일 하나로 합치기
		String birthyear = req.getParameter("birthyear");
		String birthmonth = req.getParameter("birthmonth");
		String birthdate = req.getParameter("birthdate");
		String birthDay = birthyear + birthmonth + birthdate;
		String id = new MemberDAO().findId(name, email, birthDay);
		
		if(id == null) {	// 정보와 맞는 아이디가 없을 경우 뒤로 페이지 이동하고 메시지 출력
			JSFunction.alertBack(resp, "다시 입력해주세요");
		} else {			// 정보와 맞는 아이디가 있을 경우 페이지 이동 후 아이디 알려줌
			resp.sendRedirect("idFinder-RS.jsp?id=" + id);
		}
	}
	
}