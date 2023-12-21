package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.JSFunction;
import model.MemberDAO;
import model.MemberDTO;

public class LoginServlet extends HttpServlet {
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String loginChk = req.getParameter("loginChk");
		
		// 마지막 접속일
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
		String lastVisitDate = formatter.format(date);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.loginCheck(id, pw);
		MemberDTO dto = dao.getMember(id);
		
		if(result == 1) {		// 로그인 성공시 유저 정보 세션에 저장 후 메인화면으로 이동
			dao.update_LastVisitDate(id, lastVisitDate);
			req.getSession().setAttribute("memberDTO", dto);
			req.getSession().setAttribute("userId", id);
			
			// 사용자의 로그인 유지 여부를 null 체크로 확인 
			if (loginChk != null) { // 체크한 경우
				Cookie c = new Cookie("id", id);
				c.setMaxAge(60 * 5);
				c.setPath("/");
				resp.addCookie(c);
			}
			
			resp.sendRedirect("main.jsp");
			System.out.println("로그인 성공");
			
		} else {					// 로그인 실패시
			
			int result2 = dao.checkDuplicateId(id);
			
			if(result2 == 0) {		// 아이디가 존재하지 않을 경우
				JSFunction.alertBack(resp, "아이디가 존재하지 않습니다");
			} else {				// 비밀번호가 틀릴 경우
				JSFunction.alertBack(resp, "비밀번호를 다시 확인해주세요");
			}
				
		}
	
		dao.close();
	}
	
	
}