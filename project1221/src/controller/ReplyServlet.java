package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReplyDAO;
import model.ReplyDTO;

public class ReplyServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String uri = req.getRequestURI();					// 절대 경로 (현재 파일 이름 포함)
		String Path = req.getContextPath();					// 절대 경로 (현재 파일 이름 미포함)
		String command = uri.substring(Path.length()+1);	// 슬래시 제외 현재 파일 이름
		

		if(command.equals("registerReply.reply")) {		// 단기크루 댓글 입력후 이동
			register(req, resp);
			return;
		} else if(command.equals("registerReplyLong.reply")) {			// 장기크루 댓글 입력후 이동
			register2(req, resp);
		}
		
		
	}
		// 게시판 댓글 등록(단기 크루)
		public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
			String id = req.getParameter("id");
			int b_no = Integer.parseInt(req.getParameter("b_no"));
			String content = req.getParameter("content");
		
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String created = formatter.format(now);
			
			ReplyDTO dto = new ReplyDTO();
			dto.setB_no(b_no);
			dto.setContent(content);
			dto.setCreated(created);
			dto.setId(id);
			ReplyDAO dao = new ReplyDAO();
			dao.registerReply(dto);
			dao.close();
			resp.sendRedirect("CrewBoardPage.jsp?no=" + b_no);
		}

		// 게시판 댓글 등록(장기 크루)
		public void register2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			
			String id = req.getParameter("id");
			int b_no = Integer.parseInt(req.getParameter("b_no"));
			String content = req.getParameter("content");
			
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String created = formatter.format(now);
			
			ReplyDTO dto = new ReplyDTO();
			dto.setB_no(b_no);
			dto.setContent(content);
			dto.setCreated(created);
			dto.setId(id);
			ReplyDAO dao = new ReplyDAO();
			dao.registerReply(dto);
			dao.close();
			resp.sendRedirect("CrewBoardPage.jsp?no=" + b_no);
		}
		
		

}
