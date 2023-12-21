package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RecommendDAO;

public class RecommendServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
   }
   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      int no =  Integer.parseInt(request.getParameter("no"));
      String id = (String) request.getSession().getAttribute("userId");
      RecommendDAO dao3 = new RecommendDAO();
      int result = dao3.updateRecommendCount(no, id);
      

      
      if(result == 1) {
         int newRecommend = dao3.SelectRecommendCount(no);
         dao3.close();
         System.out.println("newRecommend :" + newRecommend);
         response.setContentType("text/plain");
         response.setCharacterEncoding("utf-8");
         response.getWriter().write(String.valueOf(newRecommend));
      } else {
         int newResult = dao3.deleteRecommendCount(no, id);
         if(newResult == 1) {
            int newRecommend = dao3.SelectRecommendCount(no);
            dao3.close();
            System.out.println("newRecommend :" + newRecommend);
            response.setContentType("text/plain");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write("already" + String.valueOf(newRecommend));
         } else { 
          dao3.close();
          response.setContentType("text/plain");
          response.setCharacterEncoding("utf-8");
          response.getWriter().write("error");
         }
      }
      
//      if(result == 1) {
//         crewName = URLEncoder.encode(crewName, "UTF-8");
//         // response.sendRedirect("crewBoardList.jsp?crewName=" + crewName);
//         System.out.println("추천 업데이트 성공");
//      } else {
//         crewName = URLEncoder.encode(crewName, "UTF-8");
//         // response.sendRedirect("crewBoardList.jsp?crewName=" + crewName);
//         System.out.println("추천 업데이트 실패");
//      }
      
   }

}