<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Edu+VIC+WA+NT+Beginner:wght@400;600;700&family=Nunito:wght@300;500;700;800&family=Sono:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>Details of ${item.name}</title>

</head>
<body>




<%@include file="header.jsp" %>

    <div id="header" >

    </div>
    <div id="container">
   ${item.name}
   <br>
   	<c:forEach items="${item.getPictures()}" var="picture">
		<img src="/item-pictures/${picture.getName()}" alt="" width="300" height="230">
		</c:forEach>
		<br>
		<div>Category:</div><p>${item.category}</p>
		<div>Description:</div><p>${item.description}</p>
		<div >Price:</div><p>${item.price}</p>
		<div >Amount left:</div><p>${item.amount}</p>
		<br>
		<form action="/addToCart" method="post">
		<input type="hidden" name="itemId" value="${item.id}">
		<input type="submit" value="Add to cart">
		</form>
		<form action="/newMessage" method="post">
		<input type="hidden" name="username" value="${item.getVendor().username}">
		<input type="submit" value="Ask vendor">
		</form>
		<br>
		${message}

    </div>



</body>
</html>
