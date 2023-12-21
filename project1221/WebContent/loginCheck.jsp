<%@page import="common.JSFunction"%>
<%@page import="model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_Id = (String)session.getAttribute("userId");
	
	if(user_Id == null) {
		JSFunction.alertLocation("로그인이 필요합니다", "login.jsp", out);
		return;
	}
%>