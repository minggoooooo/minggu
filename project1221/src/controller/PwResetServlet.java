package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;
import model.MemberDAO;

public class PwResetServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String prevPw = req.getParameter("prevPw");
		String pw = req.getParameter("pw");
		String pwCheck = req.getParameter("pwCheck");
		
		
		int result = 0;
		
		if(!prevPw.equals(pw)) {
			result = new MemberDAO().resetPw(id, pw);
			
			if(result == 1) {		// 비밀번호 변경 성공시 로그인 페이지로 이동
				JSFunction.alertLocation(resp, "비밀번호가 변경되었습니다", "login.jsp");
				System.out.println("비밀번호 변경 성공");
				
			} else {				// 비밀번호 변경 실패시 이전 페이지로 이동
				JSFunction.alertBack(resp, "비밀번호를 다시 설정해주세요");
				System.out.println("비밀번호 변경 실패");
			}
			
		} else {					// 이전과 비밀번호 같을시 다시 이전 페이지로 이동
			JSFunction.alertBack(resp, "비밀번호를 이전과 다르게 설정해주세요");
		}
		
		
	
		
	}

}