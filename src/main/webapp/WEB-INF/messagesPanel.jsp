<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Edu+VIC+WA+NT+Beginner:wght@400;600;700&family=Nunito:wght@300;500;700;800&family=Sono:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>Messages</title>

</head>
<body>
	<%@include file="profilePanel.jsp"%>

<div id="menu">
<ul>
<li>
	<form action="/newMessage" method="post">
		<input type="submit" value="New message">
		</form>
</li>
<li>
<form action="/messages" method="get">
		<input type="submit" value="Received">
	</form>
</li>
<li>
<form action="/messagesSent" method="get">
		<input type="submit" value="Sent">
	</form>
	</li>
	</ul>
</div>
</body>
</html>
