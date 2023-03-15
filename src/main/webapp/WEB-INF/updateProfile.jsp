<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css2?family=Edu+VIC+WA+NT+Beginner:wght@400;600;700&family=Nunito:wght@300;500;700;800&family=Sono:wght@200;300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<style><%@include file="/WEB-INF/css/style.css"%></style>
S
<title>Konto użytkownika</title>
</head>
<body>

	<%@include file="header.jsp"%>
	<div id="container">

   <div class="toRight">

		<a class="icons" href="/logout">Log out</a> <br>
		</div>
<c:if test="${checkInfo}">  
<div class=center>
   <form action="/admin/panel" method="get">
   <input type="submit" name="gotopanel" value="Panel administracyjny">
   </form>
   </div>
</c:if> 
<form action="profile" method="post">
		<table class="center">
		
				<tr>
					<td><label for="firstname">First name: </label></td>
					<td><input type="text" name="firstName" value="${userFirstName}"></td>
				</tr>
		
				<tr>
					<td><label for="lastname">Last name: </label></td>
					<td><input type="text" name="lastName" value="${userLastName}"></td>
				</tr>
		
				<tr>
					<td><label for="email">E-mail: </label></td>
					<td><input type="text" name="email" value="${userEmail}"></td>
				</tr>
		
				<tr>
					<td><label for="telephone">Telephone: </label></td>
					<td><input type="text" name="phoneNumber" value="${userPhoneNumber}"></td>
	

		</table>
		<br>
		
]		
		      <input type="submit"  value="Submit">
      </form>
 </div>
		<br>





</body>
</html>