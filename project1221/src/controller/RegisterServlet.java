package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		/*
		 * ServletContext application = req.getServletContext();
		 * 
		 * String saveDirectory = application.getRealPath("./resources/img"); int
		 * maxPostSize = 5 * 1024 * 1024; String encoding = "UTF-8";
		 * 
		 * MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize,
		 * encoding, new DefaultFileRenamePolicy());
		 */
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		
		// 성별
		String sex = req.getParameter("sex");
		
		// 생년월일 하나로 합치기
		String birthyear = req.getParameter("birthyear");
		String birthmonth = req.getParameter("birthmonth");
		String birthdate = req.getParameter("birthdate");
		String birthDay = birthyear + birthmonth + birthdate;
		
		// 이메일 한 주소로 합치기(직접입력 or 선택)
		String email = "";
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		String emailList = req.getParameter("emailList");
		
		email += email1 + "@" + email2;
		
		
		String nickname = req.getParameter("nickname");
		if(nickname == "") {
			nickname = id;
		}
		String phone = req.getParameter("phone");
		
		// 가입날짜 설정
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String registerDate = formatter.format(now);
		
		// 마지막 접속일
		Date date = new Date();
		SimpleDateFormat formatter2 = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String lastVisitDate = formatter2.format(date);
		
		// 프로필 사진
		/*
		 * String imgName = mr.getFilesystemName("profileImg"); File file = new
		 * File(saveDirectory + File.separator + imgName); String profileImg = "";
		 */
		
		// 프로필사진 설정안하면 자동으로 기본이미지 들어감
		/*
		 * if(imgName == null) { profileImg = "zzanggu.jpg"; } else { profileImg =
		 * imgName; }
		 */
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPassword(pw);
		dto.setName(name);
		dto.setBirthDate(birthDay);
		dto.setEmail(email);
		dto.setNickname(nickname);
		dto.setPhone(phone);
		dto.setRegisterDate(registerDate);
		dto.setLastVisitDate(lastVisitDate);
		dto.setGender(sex);
		// dto.setProfileImg(profileImg);
		
		int result = new MemberDAO().registerMember(dto);
		
		if(result == 1) {		// 회원가입 성공했을 경우 유저 정보 세션에 저장 후 로그인 상태로 메인페이지 이동
			req.getSession().setAttribute("memberDTO", dto);
			resp.sendRedirect("main.jsp");
			System.out.println("회원가입 성공");
			
		} else {				// 회원가입 실패했을 경우
			JSFunction.alertBack(resp, "회원가입에 실패했습니다");
			System.out.println("회원가입 실패");
		}
	}
	
	
}