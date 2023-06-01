<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://fonts.googleapis.com/css2?family=Edu+VIC+WA+NT+Beginner:wght@400;600;700&family=Nunito:wght@300;500;700;800&family=Sono:wght@200;300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<style><%@include file="/WEB-INF/css/style.css"%></style>


<title>Contact</title>

</head>
<body>
	<%@include file="header.jsp"%>
	<div id="container">
		

			<p>Contact with us:</p>
			<br>
			<form action="/sendMail" method="post">
				<div>

					<table class="center">
						<tr>
							<td><label for="firstname">FirstName: </label></td>
							<td><input type="text" id="first"
								name="firstName" placeholder="" value="${userFirstName}"
								autocomplete="off" required /></td>
						</tr>

						<tr>
							<td><label for="lastname">LastNAme: </label></td>
							<td><input type="text" id="last"
								name="lastName" value="${userLastName}" autocomplete="off"
								required /></td>
						</tr>

						<tr>
							<td><label for="email">E-mail: </label></td>
							<td><input type="email" id="email"
								pattern="[^@\\\\\\\\\s]+@[^@\s]+\.[^@\s]+" name="email"
								value="${userEmail}" autocomplete="off" required /></td>
						</tr>

						<tr>
							<td><label for="telephone">Telephone: </label></td>
							<td><input type="text" id="PhoneNumber"
								name="phoneNumber" value="${userPhoneNumber}" autocomplete="off"
								required /></td>
						</tr>
						<tr>
							<td><label for="lastname">Subject: </label></td>
							<td><input  type="text" id="subj"
								name="subject"  autocomplete="off" required /></td>
						</tr>
						<tr>
							<td>Body:</td>
							<td><textarea rows="5" cols="32" name="body" size="150"
									value="" required></textarea></td>
						</tr>
					</table>
					<br>
					<div class="center">
						<input type="submit" class="registerbtn" value="Send">
					</div>
				</div>
			</form>
		
	</div>


</body>
</html>
