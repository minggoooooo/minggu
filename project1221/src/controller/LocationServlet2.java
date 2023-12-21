package controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class LocationServlet
 */
@WebServlet("/LocationServlet2")
public class LocationServlet2 extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LocationServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String currentlat = request.getParameter("currentlat");
		String currentlng = request.getParameter("currentlng");
		String routeListStr = request.getParameter("routeList");
		String routeForDistanceStr = request.getParameter("routeForDistance");
		if(request.getParameter("selectedValue")!=null && !request.getParameter("selectedValue").equals("undefined")) {
			String selectedValue = request.getParameter("selectedValue");
			request.setAttribute("selectedValue", selectedValue);
			System.out.println("selectedValue: " + selectedValue);
		}
		if(request.getParameter("selectedBar")!=null) {
			String selectedBar = request.getParameter("selectedBar");
			request.setAttribute("selectedBar", selectedBar);
			System.out.println("selectedBar: " + selectedBar);
		}
		if(request.getParameter("address")!=null) {
			String address = request.getParameter("address");
			request.setAttribute("address", address);
			System.out.println("address :" + address);
		}
		
		// 문자열을 배열로 변환
		Gson gson = new Gson();
		String[] routeList = gson.fromJson(routeListStr, String[].class);
		String[] routeForDistance = gson.fromJson(routeForDistanceStr, String[].class);

		// 받아온 데이터 사용 예시
		System.out.println("currentlat: " + currentlat);
		System.out.println("currentlng: " + currentlng);
		System.out.println("Route List: " + Arrays.toString(routeList));
		System.out.println("Route for Distance: " + Arrays.toString(routeForDistance));
		
		request.setAttribute("routeList", routeList);
		request.setAttribute("routeForDistance", routeForDistance);
		request.setAttribute("currentlat", currentlat);
		request.setAttribute("currentlng", currentlng);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("trangle2.jsp");
		dispatcher.forward(request, response);
	}

}
