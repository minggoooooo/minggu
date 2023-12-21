package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.digester.SystemPropertySource;

import model.CourseDAO;
import model.CourseRecommendDAO;

/**
 * Servlet implementation class recommendServlet
 */
@WebServlet("/CourseRecommendServlet")
public class CourseRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseRecommendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아이디 값으로 비교 할거임
		
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
			String login_id = request.getParameter("login_id");
			CourseRecommendDAO dao = new CourseRecommendDAO();
		//  얘는 id와 loginId를 uk로 가지고 있는 테이블입니다.
			int result = dao.addRecommend(login_id,id);
			
			System.out.println(id);
			System.out.println(login_id);
			System.out.println(result);
		
		//일단은 테스트로 바로 업데이트만 할게요
		
		if(result == 1) {
			int newRecommend = dao.getRecommend(id);
			dao.close();
			System.out.println("newRecommend :" + newRecommend);
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(String.valueOf(newRecommend));
		} else {
		    dao.close();
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("utf-8");
		    response.getWriter().write("alreadyRecommended");
		}
	}

}
