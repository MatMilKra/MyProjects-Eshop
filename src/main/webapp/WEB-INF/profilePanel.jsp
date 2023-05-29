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



<title>User's account</title>
</head>
<body>

	<a href="/logout">Log out</a> <br> 
	<div id="menu">
		<ul>
		<li>
		<form action="/add" method="get">
					<input type="submit" value="Add item">
				</form>
		</li>
			<li>
				<form action="/myItems" method="get">
					<input type="submit" value="My items">
				</form>
			</li>
			<li>
				<form action="/myCart" method="get">
					<input type="submit" value="My cart">
				</form>
			</li>
			<li>
				<form action="bought" method="post">
					<input type="submit" value="Buyed items">
				</form>
			</li>
			<li>
				<form action="/messages" method="get">
					<input type="submit" value="Messages">
				</form>
			</li>
			<li>
				<form action="/goToUpdateProfile" method="get">
					<input type="submit" value="Update profile">
				</form>
			</li>
		</ul>

	</div>


</body>
</html>