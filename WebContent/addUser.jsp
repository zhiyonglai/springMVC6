<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function addUser(){
		var form = document.forms[0];
		form.action="/springMVC6/user/data/addUser";
		form.method="post"; 
		form.submit();
	}
	
</script>
</head>
<body>
	<h1>添加用户</h1><br>
	<form action="">
		<input type="text" name="userName">
		<input type="password" name="password">
		
		<input type="button" value="添加" onclick="addUser()">  
	</form> 
</body>
</html>