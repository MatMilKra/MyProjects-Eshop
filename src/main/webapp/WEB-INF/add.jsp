<%@page import="java.text.DateFormat"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
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

<title>Rybki</title>

</head>
<body>

	<%@include file="header.jsp"%>


	<div id="container">

		${message}

		<form action="/add" method="post" enctype="multipart/form-data">

			<input type="text" name="name" placeholder="Name"> <select
				name="category">
				<option value="">Category</option>
				<c:forEach items="${categories}" var="category">
					<option value="${category}">${category}</option>
				</c:forEach>
			</select>
			<div>
				<input type="text" name="price" placeholder="Price">
			</div>


			<input type="text" name="amount" placeholder="Amount">
			<div>
				<textarea rows="5" cols="50" name="description" size="150"
					placeholder="Description maximum 150 words&#9998;" value=""></textarea>
			</div>


			<div>
				<input type="file" name="imageFile">
			</div>


			<div>
				<input type="submit" href="/item/${item.id}" value="Add">
			</div>
		</form>





	</div>
</body>
</html>
