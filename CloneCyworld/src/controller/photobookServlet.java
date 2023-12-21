package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import filter.JSFunction;
import model.photobook;
import model.photobookDAO;


@WebServlet("/photobookServlet")
public class photobookServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String saveDirectory = request.getServletContext().getRealPath("/resources/img");;
		response.setContentType("text/html; charset=UTF-8");
		int maxPostSize = 1024 * 1024 * 10;
		String encoding = "utf-8";
		
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		String owner_id = mr.getParameter("id");
		String title = mr.getParameter("title");
		String imageName = mr.getFilesystemName("photo");
		String content = mr.getParameter("content");
		
		photobook pb = new photobook();
		pb.setOwner_id(owner_id);
		pb.setTitle(title);
		pb.setImageName(imageName);
		pb.setContent(content);
		
		photobookDAO dao = new photobookDAO();
		int result = dao.insertPhotobook(pb);
		
		if(result==1) {
			JSFunction.alertLocation(response, "등록성공", "photobook.jsp?id="+owner_id);
		} else {
			JSFunction.alertLocation(response, "등록실패", "photobook.jsp?id="+owner_id);
		}
		
	}

}
