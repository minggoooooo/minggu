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
import model.BoardDAO;
import model.BoardDTO;
import model.MemberDTO;

public class EditBoardServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; chatset=UTF-8");

		ServletContext application = req.getServletContext();

		String saveDirectory = application.getRealPath("./resources/img");
		int maxPostSize = 5 * 1024 * 1024;
		String encoding = "UTF-8";

		MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding,
				new DefaultFileRenamePolicy());

		BoardDTO board = (BoardDTO) req.getSession().getAttribute("boardDTO");
		MemberDTO member = (MemberDTO) req.getSession().getAttribute("memberDTO");
		String userId = member.getId();

		String id = board.getId(); // 작성자
		System.out.println(id);
		int no = board.getNo(); // 게시판 넘버

		String content = mr.getParameter("content"); // 수정된 내용
		String category = mr.getParameter("category"); // 수정된 카테고리

		String imgName = mr.getFilesystemName("fileImg");// 수정된 이미지파일
		File file = new File(saveDirectory + File.separator + imgName);

		LocalDateTime today = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String created = today.format(formatter); // 수정일

		BoardDTO dto = new BoardDTO();
		dto.setId(id);
		dto.setNo(no);
		dto.setContent(content);
		dto.setCreated(created);
		dto.setCategory(category);
		dto.setImgName(imgName);

		int result = new BoardDAO().editBoard(dto);
		if (userId.equals(id)) { // 로그인 유저와 작성자가 같으면 수정 실행
			if (result == 1) {
				JSFunction.alertLocation(resp, "게시글이 수정되었습니다", "longCrewBoardPage.jsp?id=" + id + "&no=" + no);
				req.getSession().removeAttribute("boardDTO");
				System.out.println("게시판 수정 성공");
			} else {
				System.out.println("게시판 수정 실패");
			}
		} else { 				// 로그인 유저와 작성자가 다르면 수정 x
			JSFunction.alertBack(resp, "수정 권한이 없습니다");
		}

	}
}