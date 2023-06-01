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
<%@include file="header.jsp" %>

	<div class="container">
<%@include file="messagesPanel.jsp" %>

    
     
       <table>
				<tr>
					<td>From</td>
					<th>Subject</th>
					

				</tr>

				<c:forEach items="${messages}" var="mess">

					<tr>
						<td>${mess.getFrom().username}</td>
						<td>${mess.subject}</td>
						<td>
							<form action="/message" method="post">
						<input type="hidden" name ="messId" value="${mess.id}">
						<input type="submit" value="Open">
					</form>
						</td>
					</tr>
				</c:forEach>
			</table>
   
   
   


  
  
 </div>
</body>
</html>
