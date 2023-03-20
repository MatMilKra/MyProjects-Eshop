<%@page import="java.text.DateFormat"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar" %> 
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
					<td>${fromTo}</td>
					<th>Subject</th>
					

				</tr>
					<tr>
						<td>${userFrom.username}</td>
						<td>${subject}</td>
						<td>
						<td>
						
							<c:choose>
				 <c:when test="${fromTo == from}">
<form action="/messageReply" method="post">
	<input type="hidden" name="username" value="${userTo.username}">
	<input type="hidden" name="subject" value="${subject}">
	<input type="hidden" name="body" value="${body}">
	<input type="submit" value="New message to this user">
						</form>
       
    </c:when>    
    <c:otherwise>
<form action="/messageReply" method="post">
	<input type="hidden" name="username" value="${userFrom.username}">
		<input type="hidden" name="subject" value="${subject}">
	<input type="hidden" name="body" value="${body}">
	<input type="submit" value="Reply">
						</form>      
    </c:otherwise>
    </c:choose>
						
						
						</td>
					</tr>
					<tr>
					<td>
					${body}
					</td>
					</tr>
			</table>
   
   
   


  
  
 </div>
</body>
</html>
