<%@page import="java.io.PrintWriter" import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%
String content=request.getParameter("content");
String title=request.getParameter("title");

Connection conn=null;
PreparedStatement pstmt=null;
ResultSet rs=null;
int row=0;
String dbURL = "jdbc:mysql://localhost:3306/mbsdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
String dbID = "root";
String dbPassword = "1248";
String query="INSERT INTO board(time, title, contents, writerID) VALUES(?, ?, ?, ?)";
Class.forName("com.mysql.cj.jdbc.Driver");
try{
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	pstmt=conn.prepareStatement(query);
	pstmt.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
	pstmt.setString(2,title);
	pstmt.setString(3,content);
	pstmt.setInt(4,1);
	pstmt.executeUpdate();
	}catch (Exception e) {
			e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
PrintWriter script = response.getWriter();
script.println("<script>");
script.println("alert('저장 성공!')");
script.println("location.href='board.jsp'");
script.println("</script>");

%>

</body>
</html>