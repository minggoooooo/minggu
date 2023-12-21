package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;
import model.MemberDAO;

public class DeleteMemberServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String pwCheck = req.getParameter("pwCheck");
		
		if(pw.equals(pwCheck)) {	// 입력한 비밀번호가 일치하면 회원탈퇴 진행
			int result = new MemberDAO().deleteMember(id, pw);
			
			if(result == 1) {		
				JSFunction.alertLocation(resp, "회원탈퇴가 완료되었습니다", "main.jsp");
				System.out.println("회원탈퇴 성공");
			} else {
				System.out.println("회원탈퇴 실패");	
			}	
		} else {					// 입력한 비밀번호가 일치하지 않으면 다시 입력하도록 돌아감
			JSFunction.alertBack(resp, "비밀번호가 일치하지 않습니다");
		}
		
	}
	
	
}