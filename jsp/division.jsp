<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	
	div.button{
		margin-left: 45%;
		margin-top: 30px;
	}
	div.button input	
	{

  	 	padding: 5px;

  	 	width:200px;

   		font-size: 18px;
   		 background-color: rgb(83, 154, 236); 
   	 	border: 2px; 
    	border-radius: 3px; 
    	color: white; 
    	font-size: 22px; 
    	font-weight: 500;
	}
  .welcome{
    font-size: 40px;
    margin-left: 45%;
  margin-top: 300px;
  }
</style>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <div class="welcome">ȯ���մϴ�.</div>
	<div class="button">

    <input type="button" value="�������� ����" onClick="location.href='NotDiv.html'"/><br/><br/>
     <input type="button" value="2����" onClick="location.href='2Div.html'"/><br/><br/>
      <input type="button" value="4����" onClick="location.href='4Div.html'"/><br/>

</div>
</body>
</html>