package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;
import model.PhotoDAO;

public class DeletePhotoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int no = Integer.parseInt(req.getParameter("no"));
		int result = new PhotoDAO().deletePhoto(no);
		
		if(result == 1) {
			JSFunction.alertLocation(resp, "사진이 삭제되었습니다", "album.jsp");
			System.out.println("사진 삭제 성공");
		} else {
			System.out.println("사진 삭제 실패");
		}
		
		
		
	}
	
	
}