<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
	*{ margin: 0; padding: 0; }

	.board_wrap .header {  font-size: 25px; 
		padding: 10px;
		background-color: lightblue; 
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
		background-color: lightblue;
        border: 2px; 
        border-radius: 3px; 
        color: black; 
        width: 50px;
        height: 30px;
	}
</style>
<title>Insert title here</title>
<%
	int idx=Integer.parseInt(request.getParameter("idx"));
%>
</head>
<body>
<div class="board_wrap">
		<div class="header">
			NEW MEMO
		</div>
		<form method = "get" action = "memoNewAction.jsp">
			<table  style="padding-top:50px" align = center width=700 border=0 cellpadding=2 >
				<tr>
					<td bgcolor=white>
						<table class = "table2">	
								<td><textarea name = content style="width: 97%; height: 300px;">
								</textarea></td>
						</table>
						<input style="display:none" name="idx" type="text" value="<%=idx%>">
						<center>
							<input class="btn" type = "submit" value="저장">
						</center>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>