package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.JSFunction;
import model.MemberDAO;
import model.MemberDTO;

public class UpdateMemberServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		ServletContext application = req.getServletContext();
		
		String saveDirectory = application.getRealPath("./resources/images");
		int maxPostSize = 5 * 1024 * 1024;
		String encoding = "UTF-8";
		
		MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
		
		// 아이디
		String id = mr.getParameter("id");
		
		// 비밀번호
		String pw = mr.getParameter("pw");

		// 이메일 수정하지 않으면 기존 이메일 주소 그대로 db에 넣고 아니면 수정된 이메일 넣음
		String newEmail = "";
		String email1 = mr.getParameter("email1");
		String email2 = mr.getParameter("email2");
		
		if(email1.equals("")) { 
			  newEmail = mr.getParameter("originalEmail"); 
		 } else {
			  newEmail += email1 + "@" + email2; 
		 }
		
		String phone = mr.getParameter("phone");
		
		// 생년월일 합치기
		String newBirthday = "";
		String birthyear = mr.getParameter("birthyear");
		String birthmonth = mr.getParameter("birthmonth");
		String birthdate = mr.getParameter("birthdate");
		System.out.println("원래 생일 : " + mr.getParameter("originalBirthday"));
		System.out.println("새로 입력한 생일 : " + birthyear + birthmonth + birthdate);
		
		if(birthyear.equals("") || birthmonth.equals("") || birthdate.equals("")) {
			newBirthday = mr.getParameter("originalBirthday");
		} else {
			newBirthday = birthyear + birthmonth + birthdate;
		}
	 
		
		String nickname = mr.getParameter("nickname");
		
		// 프로필 사진 수정 안했으면 강아지 사진 기본으로 설정
		String newProfileImg = "";
		String newImg = mr.getParameter("newImg");
		String defaultImg = "dog.png"; 
		
		if(newImg == null) {
			newProfileImg = defaultImg;
		} else {
			newProfileImg = newImg;
		}
		
		String newGender = "";
		String gender = mr.getParameter("sex");
		if(gender == null) {
			newGender = mr.getParameter("originalGender");
		} else {
			newGender = gender;
		}
		
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPassword(pw);
		dto.setBirthDate(newBirthday);
		dto.setEmail(newEmail);
		dto.setPhone(phone);
		dto.setNickname(nickname);
		dto.setProfileImg(newProfileImg);
		dto.setGender(newGender);
		
		int result = new MemberDAO().updateMember(dto);
		if(result == 1) {
			JSFunction.alertLocation(resp, "정보가 수정되었습니다", "main.jsp");
			System.out.println("회원정보 수정 성공");
		} else {
			System.out.println("회원정보 수정 실패");
		}
	}
	
}