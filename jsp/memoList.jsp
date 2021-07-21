<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<%
int writerID=0;
Connection conn=null;
Statement stmt=null;
ResultSet rs=null;
String dbURL = "jdbc:mysql://localhost:3306/mbsdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
String dbID = "root";
String dbPassword = "1248";
Class.forName("com.mysql.cj.jdbc.Driver");
try{
	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
	stmt=conn.createStatement();
	String query="Select memoID, memoContent, time, writerID, userID FROM memo, employee WHERE writerID=EmpID";
	rs=stmt.executeQuery(query);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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

body {
	line-height: 2em;
	font-family: "맑은 고딕";
}

ul, li {
	list-style: none;
	text-align: center;
	padding: 0;
	margin: 0;
}

#mainWrapper {
	width: 100%;
	margin: 0 auto;
	height: 100%;
}

#mainWrapper>ul>li:first-child {
	text-align: center;
	font-size: 14pt;
	height: 40px;
	vertical-align: middle;
	line-height: 30px;
}

#ulTable {
	margin-top: 10px;
}

#ulTable>li:first-child>ul>li {
	background-color: lightblue;
	font-weight: bold;
	text-align: center;
}

#ulTable>li>ul {
	clear: both;
	padding: 0px auto;
	position: relative;
	min-width: 40px;
}

#ulTable>li>ul>li {
	float: left;
	font-size: 10pt;
	border-bottom: 1px solid silver;
	vertical-align: baseline;
}

#ulTable>li>ul>li:first-child {
	width: 10%;
} /*No 열 크기*/
#ulTable>li>ul>li:first-child+li {
	width: 50%;
} /*제목 열 크기*/
#ulTable>li>ul>li:first-child+li+li {
	width: 30%;
} /*작성일 열 크기*/
#ulTable > li > ul > li:first-child +li+li+li     {width:10%;}
#divPaging {
	clear: both;
	margin: 0 auto;
	width: 220px;
	height: 50px;
}

#divPaging>div {
	float: left;
	width: 30px;
	margin: 0 auto;
	text-align: center;
}

#divPaging>input {
	
}

#liSearchOption {
	clear: both;
}

#liSearchOption>div {
	margin: 0 auto;
	margin-top: 30px;
	width: auto;
	height: 100px;
}

.btn_srch {
	background-color: lightblue;
	border: 2px;
	border-radius: 3px;
	color: black;
	width: 30px;
}

.btn_new {
	background-color: lightblue;
	border: 2px;
	border-radius: 3px;
	color: black;
	width: 60px;
}

.left {
	text-align: left;
}

.new {
	margin: 0 auto;
}

.btndel {
	background-color: lightblue;
	border: 2px;
	border-radius: 3px;
	color: black;
	width: 40px;
}
</style>
<title>MemoList</title>
</head>

<body>
	<div class="board_wrap">
		<div class="header">MEMOLIST</div>
		<div id="mainWrapper">
			<li>
				<ul id="ulTable">
					<li>
						<ul>
							<li>No</li>
							<li>내용</li>
							<li>작성일</li>
							<li>작성자</li>
						</ul>
					</li>
					<!-- 게시물이 출력될 영역 -->
					<%while(rs.next()){ %>
					<li>
						<ul>
							<li><%=rs.getInt("memoID") %></li>
							<li onClick="location.href='memoUpdate.jsp?idx=<%=rs.getInt("memoID")%>'" 
								class="left"><%=rs.getString("memoContent")%></li>
							<li><%=rs.getTimestamp("time") %>
								<input type="button" onClick="location.href='memoDeleteAction.jsp?idx=<%=rs.getInt("memoID")%>'" class="btndel" value="삭제">
							</li>
							<li><%=rs.getString("userID")%></li>
						</ul>
					</li>
					<%writerID=rs.getInt("writerID");
						} %>
					

				</ul> <!-- 게시판 페이징 영역 --> <!-- 검색 폼 영역 -->
				<div>
					<input type="button" onClick="location.href='memoNew.jsp?idx=<%=writerID%>'" class="btn_new" type="button" value="새 메모">
				</div>
			</li>
		</div>
	</div>
	<%
					rs.close();
					stmt.close();
					conn.close();
					}catch (Exception e) {
							e.printStackTrace();
					}
	%>
</body>
</html>