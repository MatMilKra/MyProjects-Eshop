<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css2?family=Edu+VIC+WA+NT+Beginner:wght@400;600;700&family=Nunito:wght@300;500;700;800&family=Sono:wght@200;300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<style><%@include file="/WEB-INF/css/style.css"%></style>



<title>Admin panel</title>
</head>
<body>


   <div class="toRight">
		 <a class="icons" href="/logout">Log out</a> <br> <br>
		 </div>
	
					<form action="/admin/users" method="get">
						<input type="submit" class="registerbtn" value="Users">
					</form>
			



		<br>




</body>
</html>