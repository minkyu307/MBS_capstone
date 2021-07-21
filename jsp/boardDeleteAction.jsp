<%@page import="java.io.PrintWriter" import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<%
int idx=Integer.parseInt(request.getParameter("idx"));

Connection conn=null;
PreparedStatement pstmt=null;
ResultSet rs=null;
int row=0;
String dbURL = "jdbc:mysql://localhost:3306/mbsdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
String dbID = "root";
String dbPassword = "1248";
String query="DELETE FROM board WHERE boardID=?";
Class.forName("com.mysql.cj.jdbc.Driver");
try{
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	pstmt=conn.prepareStatement(query);
	pstmt.setInt(1,idx);
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
script.println("alert('삭제 성공!')");
script.println("location.href='board.jsp'");
script.println("</script>");

%>

</body>
</html>