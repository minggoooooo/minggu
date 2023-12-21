package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filter.JSFunction;
import model.GuestbookReplyDAO;


@WebServlet("/deleteGuestbookReplyServelt")
public class deleteGuestbookReplyServelt extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		String id = request.getParameter("id");
		GuestbookReplyDAO dao = new GuestbookReplyDAO();
		int result = dao.deleteReply(r_no);
		if(result == 1) {
			JSFunction.alertLocation(response, "삭제 성공", "guestbook.jsp?id="+id);
		} else {
			JSFunction.alertLocation(response, "삭제 실패", "guestbook.jsp?id="+id);
		}
	}

}
