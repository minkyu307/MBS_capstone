<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*" import="request.*"%>
<%
int idx=Integer.parseInt(request.getParameter("idx"));

Connection conn=null;
PreparedStatement pstmt=null;
ResultSet rs=null;
String dbURL = "jdbc:mysql://localhost:3306/mbsdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
String dbID = "root";
String dbPassword = "1248";
String query="Select memoID, memoContent FROM memo WHERE memoID=?";
Class.forName("com.mysql.cj.jdbc.Driver");
try{
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	pstmt = conn.prepareStatement(query);
	pstmt.setInt(1,idx);
	rs=pstmt.executeQuery();
	}catch (Exception e) {
		e.printStackTrace();
	}


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.board_wrap .header {
	font-size: 25px;
	padding: 10px;
	background-color: lightblue;
	color: white;
	text-align: center;
}

table {
	width: 100%;
}

table.table2 {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: left;
	line-height: 1.5;
	border-top: 1px solid #ccc;
	margin: 20px 10px;
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

.btn {
	background-color: lightblue;
	border: 2px;
	border-radius: 3px;
	color: black;
	width: 50px;
	height: 30px;
}
</style>
</head>


<body>
	<div class="board_wrap">
		<div class="header">MEMO UPDATE</div>
		<form method="get" action="memoUpdateAction.jsp">
			<table style="padding-top: 50px; " align = center width=700 cellpadding=2>
				<tr><%rs.next(); %>
					<td bgcolor=white>
						<table class="table2">
							<input style="display:none" name="idx" type="text" value="<%=rs.getInt("memoID")%>">
							<td>
								<textarea name="content" style="width: 97%; height: 300px;"><%=rs.getString("memoContent") %>
								</textarea>
							</td>
						</table>
						<center>
							<input class="btn" type = "submit" value="수정">
						</center>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>