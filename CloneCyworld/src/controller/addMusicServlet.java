package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filter.JSFunction;
import model.jukebox;
import model.jukeboxDAO;

@WebServlet("/addMusicServlet")
public class addMusicServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String owner_id = request.getParameter("owner_id");
		String title = request.getParameter("title");
		String artist = request.getParameter("artist");
		String id = request.getParameter("id");
		jukebox music = new jukebox();
		music.setTitle(title);
		music.setArtist(artist);
		music.setId(id);
		music.setOwner_id(owner_id);
		
		jukeboxDAO dao = new jukeboxDAO();
		int result = dao.addMusic(music);
		
		if(result == 1) {
			response.setContentType("text/html;charset=UTF-8");

		    PrintWriter out = response.getWriter();
		    out.println("<html>");
		    out.println("<head><title>Close Window</title></head>");
		    out.println("<body>");
		    out.println("<script>");
		    out.println("alert('음악 추가 성공.');");
		    out.println("window.close();");
		    out.println("</script>");
		    out.println("</body>");
		    out.println("</html>");
		} else {
			JSFunction.alertLocation(response, "음악추가실패", "addMusic.jsp?owner_id="+owner_id);
		}
		
	}

}
