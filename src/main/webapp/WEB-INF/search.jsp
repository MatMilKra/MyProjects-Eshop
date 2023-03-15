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
<style>
<%@
include
 
file
="/
WEB-INF
/
css
/
style
.css
"%
>
</style>

<title>Rybki</title>

</head>
<body>




	<%@include file="header.jsp"%>

	<div id="container">

		<div>
			<form action="/search" method="post">
				<input class="search_tab_small" type="text" name="searchTab"
					placeholder="Find by name or in description"> <select
					name="category">
					<br>
					<option value="">Category</option>
					<c:forEach items="${categories}" var="category">
						<option value="${category}">${category}</option>
					</c:forEach>
				</select> <input type="text" name="priceMin" placeholder="Minimum price">
				<input type="text" name="priceMax" placeholder="Maximum price">
				<input type="submit" value="Search">
			</form>
		</div>
		<table>

			<tr>
				<td>Name</td>
				<th>Category</th>
				<th>Description</th>
				<th>Price</th>
			</tr>

			<c:forEach items="${items}" var="item">

				<tr>


					<td>${item.name}</td>
					<td>${item.category}</td>
					<td>${item.description}</td>
					<td>${item.price}</td>
					<td><a href="/item/${item.id}">Details</a></td>
			</c:forEach>
		</table>

	</div>



</body>
</html>
