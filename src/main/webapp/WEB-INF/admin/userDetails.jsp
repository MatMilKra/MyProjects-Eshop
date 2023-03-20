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

<style>
<%@include file="/WEB-INF/css/style.css"%>
</style>

<title>User's details</title>
</head>
<body>

	<%@include file="../header.jsp"%>


	<div id="container">
		<%@include file="panelMenu.jsp"%>
		<div class="fontblack smth">
			<div class="font_big">User's details</div>
		</div>
		<br>
	<table class="center">
	<tr>
	<td>
			<form action="/admin/setRole/${user.id}" method="post">
			
						<input type="hidden" name="id" value="${user.id}"> <select
							name="roleChoose">
							<option value="">Choose role</option>
							<c:forEach items="${roleChoose}" var="roleChoose">
								<option value="${roleChoose}">${roleChoose}</option>
							</c:forEach>
						</select>
						
						<input type="submit" value="Change role">
					</form>
					</td>
					<td>
					
								<form action="/newMessage" method="post">
		<input type="hidden" name="username" value="${user.username}">
		<input type="submit" value="Send message">
		</form>
		</td>
		</tr>
									</table>
		<table class="center">
			<tr>
		<th>Nr</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Email</th>
			<th>Telephone</th>
			<th>Role</th>
			</tr>


			<tr>
				<td>${user.id}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.email}</td>
				<td>${user.phoneNumber}</td>
				<td>${user.role}</td>
				
				
			</tr>
		</table>
<div>
<form action="/listByVendor" method="post">
<input type="hidden" name="id" value="${user.id}">
<input type="submit" value="User's items">
</form>
<form action="/admin/userBought" method="post">
<input type="hidden" name="id" value="${user.id}">
<input type="submit" value="User's bought">
</form>
</div>


	</div>
</body>
</html>