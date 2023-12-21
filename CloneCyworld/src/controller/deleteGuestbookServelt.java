package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filter.JSFunction;
import model.GuestbookDAO;
import model.GuestbookReplyDAO;


@WebServlet("/deleteGuestbookServelt")
public class deleteGuestbookServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String id = request.getParameter("id");
		GuestbookReplyDAO dao2 = new GuestbookReplyDAO();
		int count = dao2.countReply(no);
		int result2 = 0;
		
		if(count !=0 && count != -1) {	//게시물에 댓글이 있으면
			result2 = dao2.deleteReplyforbook(no);	//게시물 댓글도 삭제
			dao2.close();
			GuestbookDAO dao = new GuestbookDAO();
			int result = dao.deleteGuestbook(no);	// 게시물 삭제
			dao.close();
			if(result == 1 && result2 !=0) {
				JSFunction.alertLocation(response, "삭제 성공", "guestbook.jsp?id="+id);
			} else {
				JSFunction.alertLocation(response, "삭제 실패", "guestbook.jsp?id="+id);
			}
		} else {
			dao2.close();
			GuestbookDAO dao = new GuestbookDAO();
			int result = dao.deleteGuestbook(no);	// 게시물 삭제
			dao.close();
			if(result==1) {
				JSFunction.alertLocation(response, "삭제 성공", "guestbook.jsp?id="+id);
			} else {
			JSFunction.alertLocation(response, "삭제 실패", "guestbook.jsp?id="+id);
			}
		}	
		
	}

}
