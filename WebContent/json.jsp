<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/springMVC6/js/jquery.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#add").click(function(){
			
			var userName = $("#userName").attr("value");
			var password = $("#password").attr("value");
			var user = {userName:userName,password:password};
			
			//ajax 异步提交 无刷新
			$.ajax({
				url:"/springMVC6/user/data/addUserJson",
				type:"get",
				data:user,
				success:function(date){
					alert("userName-->"+date.userName+" passsword-->"+date.password);
				}
			});
			/**
			var userName = encodeURI($("#userName").attr("value"));
			var password = encodeURI($("#password").attr("value"));
			var user = {userName:userName,password:password};
			
			//ajax 异步提交 无刷新
			$.ajax({
				url:"/springMVC6/user/data/addUserJson",
				type:"post",
				data:user,
				success:function(date){ 
					alert("userName-->"+date.userName+" passsword-->"+date.password);
				}
			});**/
			
		});
		
	});
</script>
</head>
<body>
	<h1>json添加用户</h1>
	姓名:<input type="text" id="userName" name="userName">
	密码:<input type="password" id="password" name="password">
	<input type="button" id="add" value="添加"/>
</body>
</html>