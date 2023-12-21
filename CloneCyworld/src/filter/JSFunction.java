package filter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	
		public static void alertLocation(String msg, String url, JspWriter out) {
			try {
				String script =""
						+"<script>"
						+ " alert('" + msg + "'); "
						+ " location.href='" + url +"';"
						+ "</script>";
				out.println(script);
			} catch (Exception e) {
				
			}
			
		}
		public static void alertLocation(HttpServletResponse resp, String msg, String url) {
			try {
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter writer = resp.getWriter();
				String script =""
						+"<script>"
						+ " alert('" + msg + "'); "
						+ " location.href='" + url +"';"
						+ "</script>";
				writer.println(script);
			} catch (Exception e) {
				
			}
			
		}
		
		public static void alertBack(String msg, JspWriter out) {
			try {
				String script =""
						+"<script>"
						+ " alert('" + msg + "'); "
						+ " history.back();"
						+ "</script>";
				out.println(script);
				// jspwriter out 변수로 메인으로 해서 out.메소드 사용한것
			} catch (Exception e) {
				
			}
			
		}
		
		public static void alertBack(HttpServletResponse resp, String msg) {
			try {
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter writer = resp.getWriter();
				String script =""
						+"<script>"
						+ " alert('" + msg + "'); "
						+ " history.back();"
						+ "</script>";
				writer.println(script);
				// jspwriter out 변수로 메인으로 해서 out.메소드 사용한것
			} catch (Exception e) {
				
			}
			
		}
}
