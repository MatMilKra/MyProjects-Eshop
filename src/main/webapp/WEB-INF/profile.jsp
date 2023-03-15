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

	<%@include file="header.jsp"%>
	<div id="container">



		<a href="/logout">Log out</a> <br> <a href="/add">add</a> <br>
	</div>
	<c:if test="${checkInfo}">
		<div class=center>
			<form action="/admin/panel" method="get">
				<input type="submit" name="gotopanel" value="Administration panel">
			</form>
		</div>
	</c:if>

	<table class="center">

		<tr>
			<td>Username</td>
			<td>${userUsername}</td>
		</tr>


		<tr>
			<td><label for="firstname">Imię: </label></td>
			<td>${userFirstName}</td>
		</tr>

		<tr>
			<td><label for="lastname">Nazwisko: </label></td>
			<td>${userLastName}</td>
		</tr>
		<tr>
			<td><label for="email">Adres e-mail: </label></td>
			<td>${userEmail}</td>
		</tr>

		<tr>
			<td><label for="telephone">Telefon: </label></td>
			<td>${userPhoneNumber}</td>
	</table>
	<br>

	<form action="/myItems" method="get">
		<input type="submit" value="See my items">
	</form>
	<form action="/myCart" method="get">
		<input type="submit" value="See my cart">
	</form>
	<form action="buyed" method="get">
		<input type="submit" value="See my cart">
	</form>
	<div class="inl">
		<form action="/goToUpdateProfile" method="post">
			<input type="submit" value="Edytuj dane">

		</form>

	</div>



</body>
</html>