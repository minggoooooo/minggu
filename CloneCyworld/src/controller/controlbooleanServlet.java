package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filter.JSFunction;
import model.jukeboxDAO;
@WebServlet("/controlbooleanServlet")
public class controlbooleanServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("owner_id");
		String[] list = request.getParameterValues("checkbox");
		int[] intlist = new int[list.length];
		jukeboxDAO dao = new jukeboxDAO();
		
		int result2 = 0;
		result2 = dao.reset();
		
		int result = 0;
		for(int i=0; i<list.length; i++) {
			System.out.println(list[i]);
			intlist[i] = Integer.parseInt(list[i]);
			
			result = dao.setBoolean(intlist[i]); 
			if(result != 1) { 
				break;
				}
			
		}
		dao.close();
		if (result== 1) {
		JSFunction.alertLocation(response, "등록성공", "home.jsp?id="+id);
		} else {
			JSFunction.alertLocation(response, "등록실패", "jukebox.jsp?id="+id);
		}
		
	}

}
