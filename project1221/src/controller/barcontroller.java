package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class barcontroller
 */
@WebServlet("/barcontroller")
public class barcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public barcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/plain");
	        
	        String value = request.getParameter("value");

	        // 받아온 문자열을 정수로 변환
	        int result = Integer.parseInt(value);

	        // 서블릿에서 request에 값을 저장
	        request.setAttribute("result", result);

	        // JSP 파일로 포워딩
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/trangle.jsp");
	        dispatcher.forward(request, response);	
	}

}
