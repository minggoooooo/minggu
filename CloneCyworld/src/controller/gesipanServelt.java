package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.gesipan;
import model.gesipanDAO;


@WebServlet("/gesipanServelt")
public class gesipanServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();;
		gesipanDAO dao = new gesipanDAO();
		String owner_id = session.getAttribute("user_id").toString();
		int num = dao.countGesi(owner_id);	// 게시물 개수
		ArrayList<gesipan> list = new ArrayList<>();
		list  = dao.readgesipan(owner_id);
		session.setAttribute("list", list);
	}

}
