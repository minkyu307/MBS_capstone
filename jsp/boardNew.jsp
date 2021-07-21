<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>

<head>
	<title></title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
	*{ margin: 0; padding: 0; }

	.board_wrap .header {  font-size: 25px; 
		padding: 10px;
		background-color: lightpink; 
		color: white;
		text-align: center;
	}
	table{
		width: 100%;
	}
	table.table2{
		border-collapse: separate;
		border-spacing: 1px;
		text-align: left;
		line-height: 1.5;
		border-top: 1px solid #ccc;
		margin : 20px 10px;
	}
	table.table2 tr {
		width: 50px;
		padding: 10px;
		font-weight: bold;
		vertical-align: top;
		border-bottom: 1px solid #ccc;
	}
	table.table2 td {
		width: 100px;
		padding: 10px;
		vertical-align: top;
		border-bottom: 1px solid #ccc;
	}
	.btn{
		background-color: lightpink;
        border: 2px; 
        border-radius: 3px; 
        color: black; 
        width: 70px;
        height: 30px;
	}
</style>
<%
/* int idx=Integer.parseInt(request.getParameter("idx")); 
Connection conn=null;
PreparedStatement pstmt=null;
ResultSet rs=null;
String query="Select title, contents FROM board WHERE boardID=?";
String dbURL = "jdbc:mysql://localhost:3306/mbsdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
String dbID = "root";
String dbPassword = "1248";
Class.forName("com.mysql.cj.jdbc.Driver");
try{
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	pstmt=conn.prepareStatement(query);
	pstmt.setInt(1,idx);
	rs=pstmt.executeQuery();
}catch (Exception e) {
	e.printStackTrace();
}
rs.next(); */
%>
<body>
	<div class="board_wrap">
		<div class="header">
			BOARD NEW
		</div>
		<form method = "get" action = "boardNewAction.jsp">
			<table  style="padding-top:50px" align = center width=700 border=0 cellpadding=2 >

				<tr>
					<td bgcolor=white>
						<table class = "table2">
							<tr>
								<td>제목</td>
								<td><input type = text name ="title" size=55 value=""></td>
							</tr>

							<tr>
								<td>내용</td>
								<td><textarea name ="content" cols=55 rows=12 value="" ></textarea></tr>

							<tr>
								<td>파일첨부</td>
								<td></td>
							</tr>
						</table>

						<center>
							<input class="btn" type = "submit" value="저장">
						</center>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%
	/* rs.close();
	pstmt.close();
	conn.close(); */
	%>
</body>
</html>