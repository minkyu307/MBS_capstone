<%@page import="java.io.PrintWriter"%>
<%@page import="user.User"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		User user= new User();
		user.setUserID(request.getParameter("userID"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setDeptID(request.getParameter("department"));
		user.setPosition(request.getParameter("position"));
		user.setCall(request.getParameter("call"));
		
		UserDAO userDAO=new UserDAO();
		int result = userDAO.join(user);
		if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 존재하는 아이디입니다')");
			script.println("history.back()");
			script.println("</script>");
		}else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원가입 성공')");
			script.println("location.href='login.jsp'");
			script.println("</script>");
		}
	%>
</body>
</html>