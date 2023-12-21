package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;
import model.MemberDAO;
import model.MemberDTO;

public class MemberServlet extends HttpServlet {
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();	// 절대 경로 (현재 파일 이름 포함)
		String Path = request.getContextPath();	// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(command.equals("login.member")) {
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();	// 절대 경로 (현재 파일 이름 포함)
		String Path = request.getContextPath();	// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	
	
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		// 마지막 접속일
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String lastVisitDate = formatter.format(date);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.loginCheck(id, pw);
		MemberDTO dto = dao.getMember(id);
		
		if(result == 1) {			// 로그인 성공시 유저 정보 세션에 저장 후 메인화면으로 이동
			dao.update_LastVisitDate(id, lastVisitDate);
			req.getSession().setAttribute("memberDTO", dto);
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
	
	

