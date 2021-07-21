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
		
		padding: 10px;
		vertical-align: top;
		border-bottom: 1px solid #ccc;
	}
	.btn{
		background-color: lightpink;
		border: 2px; 
		border-radius: 3px; 
		color: black; 
		width: 50px;
		height: 30px;
	}
</style>
<%
int idx=Integer.parseInt(request.getParameter("idx"));
Connection conn=null;
PreparedStatement pstmt=null;
Statement stmt=null;
ResultSet rs=null;
String query="Select boardID, title, time, Views, contents FROM board WHERE boardID=?";
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
rs.next();
String query1="UPDATE board SET Views=Views+1 WHERE boardID="+idx;
try{
	stmt=conn.createStatement();
	stmt.executeUpdate(query1);
}catch(Exception e){
	e.printStackTrace();
}
%>
<body>
	<div class="board_wrap">
		<div class="header">
			BOARD READ
		</div>
		<form method = "get">
			<table  style="padding-top:50px" align = center width=700 border=0 cellpadding=2 >

				<tr>
					<td bgcolor=white>
						<table class = "table2">
							<tr>
								<td width="150">제목</td>
								<td><%=rs.getString("title") %></td>
							</tr>
							<tr>
								<td>작성일자</td>
								<td><%=rs.getTimestamp("time") %></td>
							</tr>
							<tr>
								<td>조회수</td>
								<td><%=rs.getInt("Views") %></td>
							</tr>

							<tr>
								<td>본문</td>
								<td><%=rs.getString("contents") %></td>
							</tr>

							<tr>
								<td>파일첨부</td>
								<td></td>
							</tr>
						</table>

						<center>
							<input type="button" class="btn" 
							onClick="location.href='boardUpdate.jsp?idx=<%=rs.getInt("boardID")%>'" value="수정"></button>
							<input type="button" class="btn" 
							onClick="location.href='boardDeleteAction.jsp?idx=<%=idx%>'" value="삭제"></button>
						</center>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%
	rs.close();
	pstmt.close();
	stmt.close();
	conn.close();
	%>
</body>
</html>