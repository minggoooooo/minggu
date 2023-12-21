package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

import filter.JSFunction;
import model.GuestbookReply;
import model.GuestbookReplyDAO;

@WebServlet("/GuestbookReplyServlet")
public class GuestbookReplyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		String id = request.getParameter("id");
		String content = request.getParameter("content2");
		String owner_id = request.getParameter("owner_id");
		
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		String formattedTime = currentTime.format(formatter);
		formattedTime.substring(0, 15);
		
		GuestbookReply gbr = new GuestbookReply();
		gbr.setB_no(b_no);
		gbr.setId(id);
		gbr.setContent(content);
		gbr.setCreated(formattedTime.substring(0, 15));
		
		GuestbookReplyDAO dao = new GuestbookReplyDAO();
		int result = dao.insert(gbr);
		dao.close();
		if(result == 1) {
			JSFunction.alertLocation(response, "등록성공", "guestbook.jsp?id="+owner_id);
		} else {
			JSFunction.alertBack(response, "등록실패");
		}
	}

}
