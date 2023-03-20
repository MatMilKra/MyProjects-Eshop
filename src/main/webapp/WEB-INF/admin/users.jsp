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



<title>Users</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<div id="container">
		<%@include file="panelMenu.jsp"%>

			<div class="center">Users</div>
				<table class="center">
		<tr>
			<th>Nr</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Email</th>
			<th>Telephone</th>
			<th>Role</th>
			<th>More</th>
			</tr>

			<c:forEach items="${usersAll}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.email}</td>
					<td>${user.phoneNumber}</td>
					<td>${user.role}</td>
					<td><a href="/admin/users/${user.id}">See</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>


</body>
</html>