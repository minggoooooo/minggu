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
import model.Course;
import model.CourseDAO;


@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		
		String realFolder = request.getServletContext().getRealPath("resources/file");
		String uploadFolder = "C:\\paths";
		System.out.println(realFolder);
		int max_size = 1024 * 1024 * 5;
		String encType="utf-8";
		
		MultipartRequest mr = new MultipartRequest(request, uploadFolder, max_size, encType , new DefaultFileRenamePolicy());
		
		Course course = new Course();
		CourseDAO dao = new CourseDAO();
		int num = dao.SettingId() + 1;
		String parseNum = String.format("%09d", num);
		
		String id = "WalkingCrew" + parseNum;
		String location = mr.getParameter("location");
		String length = mr.getParameter("length");
		String expect_time = mr.getParameter("expect_time");
		String difficulty = mr.getParameter("difficulty");
		String file_id = mr.getFilesystemName("file_id");
		
		course.setId(id);
		course.setLocation(location);
		course.setLength(length);
		course.setExpect_time(expect_time);
		course.setDifficulty(difficulty);
		course.setFile_id(file_id);
		
		int result = dao.AddCourse(course);
		
		if(result==1) {
			JSFunction.alertLocation(response, "등록성공", "upload.jsp");
		} else {
			JSFunction.alertLocation(response, "등록실패", "upload.jsp");
		}
		
		dao.close();
		
		
	}

}
