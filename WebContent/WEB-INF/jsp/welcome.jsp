<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>欢迎进入springMVC开发之路</h1>
	<br>
	<h1>传递数据</h1>
	<!-- ${result } -->
	<div>
		<c:forEach items="${map }" var="m">
			${m.key }  ----->  ${m.value }
		</c:forEach>
	</div>
</body>
</html>