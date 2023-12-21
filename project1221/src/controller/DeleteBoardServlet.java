package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;
import model.BoardDAO;

public class DeleteBoardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 작성자만 삭제 가능
		String userId = req.getParameter("userId");
		String writerId = req.getParameter("writerId");
		String crewName = req.getParameter("crewName");
		int no = Integer.parseInt(req.getParameter("no"));
		
		if(userId.equals(writerId)) {	// 로그인 아이디와 작성자가 같으면 삭제 가능
			int result = new BoardDAO().deleteBoard(no);

			if(result == 1) {		// 게시물 삭제 성공
				String encodedCrewName = URLEncoder.encode(crewName, "UTF-8");
				JSFunction.alertLocation(resp, "게시물이 삭제되었습니다", "table.jsp?crewName=" + encodedCrewName);
				System.out.println("게시물 삭제 성공");
			} else {				// 게시물 삭제 실패
				JSFunction.alertBack(resp, "게시물 삭제에 실패했습니다");
				System.out.println("게시물 삭제 실패");
			}
		} else {						// 로그인 아이디와 작성자 아이디가 다르면 삭제 불가능
			JSFunction.alertBack(resp, "삭제 권한이 없습니다");
		}
	
	}
	
	
}