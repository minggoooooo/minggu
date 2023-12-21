package controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.JSFunction;
import model.PhotoDAO;
import model.PhotoDTO;

public class UpdatePhotoServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; chatset=UTF-8");
		
		ServletContext application = req.getServletContext();
		
		String saveDirectory = application.getRealPath("./resources/img");
		int maxPostSize = 5 * 1024 * 1024;
		String encoding = "UTF-8";
		
		MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());

		LocalDateTime today = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String created = today.format(formatter);			// 사진 업로드 날짜
		
		String id = mr.getParameter("id");					// 작성자
		String title = mr.getParameter("title");			// 제목
		String content = mr.getParameter("content");		// 내용
		String imgName = mr.getFilesystemName("fileImg");	// 이미지파일
		
		File file = new File(saveDirectory + File.separator + imgName);
		
		PhotoDTO dto = new PhotoDTO();
		dto.setId(id);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setCreated(created);
		dto.setImgName(imgName);
		
		/*
		 * int result = new photoDAO().insertPhoto(dto);
		 * 
		 * if(result == 1) { JSFunction.alertLocation(resp, "사진이 업로드 되었습니다",
		 * "photobook.jsp"); System.out.println("사진 등록 성공"); } else {
		 * JSFunction.alertBack(resp, "사진 업로드에 실패했습니다"); System.out.println("사진 등록 실패");
		 * }
		 */
	
	}
	
}