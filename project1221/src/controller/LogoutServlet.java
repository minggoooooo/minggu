package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JSFunction;

public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	
	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(session != null) {
			System.out.println("쿠키지우기");
			Cookie c = new Cookie("id", "");
			c.setMaxAge(0);
			c.setPath("/");
			resp.addCookie(c);             //쿠키에 초기화된 c 정보를 보냄
			session.invalidate();
		}
		JSFunction.alertLocation(resp, "로그아웃 되었습니다", "main.jsp");
	}

}