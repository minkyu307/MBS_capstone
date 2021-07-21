<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
</head>
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
	background-color: lightpink;
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
	background-color: lightpink;
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
	width: 45%;
} /*제목 열 크기*/
#ulTable>li>ul>li:first-child+li+li {
	width: 20%;
} /*작성일 열 크기*/
#ulTable>li>ul>li:first-child+li+li+li {
	width: 15%;
} /*작성자 열 크기*/
#ulTable>li>ul>li:first-child+li+li+li+li {
	width: 10%;
} /*조회수 열 크기*/
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
	background-color: lightpink;
	border: 2px;
	border-radius: 3px;
	color: black;
	width: 30px;
}

.btn_new {
	background-color: lightpink;
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
</style>
<%
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
	String query="Select boardID, title, time, userID, Views FROM board, employee WHERE writerID=EmpID";
	rs=stmt.executeQuery(query);

%>
<body>
	<div class="board_wrap">
		<div class="header">BOARD</div>
		<div id="mainWrapper">
			<li>
				<ul id="ulTable">
					<li>
						<ul>
							<li>No</li>
							<li>제목</li>
							<li>작성일</li>
							<li>작성자</li>
							<li>조회수</li>
						</ul>
					</li>
					<!-- 게시물이 출력될 영역 -->
					<%while(rs.next()){ %>
					<li>
						<ul>
							<li><%=rs.getInt("boardID") %></li>
							<li class="left"
								onClick="location.href='boardRead.jsp?idx=<%=rs.getInt("boardID")%>'"><%=rs.getString("title") %></li>
							<li><%=rs.getTimestamp("time") %></li>
							<li><%=rs.getString("userID") %></li>
							<li><%=rs.getInt("Views") %></li>
						</ul>
					</li>
					<%
						} %>
					<%
					rs.close();
					stmt.close();
					conn.close();
					}catch (Exception e) {
							e.printStackTrace();
					}
	%>
				</ul> <!-- 게시판 페이징 영역 -->
			<li>
				<div id="divPaging">
					<div>◀</div>
					<div>
						<b>1</b>
					</div>
					<div>2</div>
					<div>3</div>
					<div>4</div>
					<div>5</div>
					<div>▶</div>
				</div>

			</li>
			<!-- 검색 폼 영역 -->
			<li id='liSearchOption'>
				<div>
					<form method = "get" action = "boardSearch.jsp">
						<select name="sel" id='selSearchOption'>
							<option value='1'>제목+내용</option>
							<option value='2'>제목</option>
							<option value='3'>내용</option>
						</select> 
						<input id='txtKeyWord' name="srch" /> 
						<input class="btn_srch" type="submit" value="검색"/> 
					</form>
					<input class="btn_new" type="button" value="새글쓰기" onClick="location.href='boardNew.jsp'">
				</div>
			</li>
		</div>
</body>
<script type="text/javascript">

        </script>
</html>