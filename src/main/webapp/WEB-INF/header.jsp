<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Edu+VIC+WA+NT+Beginner:wght@400;600;700&family=Nunito:wght@300;500;700;800&family=Sono:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>E-shop - main page</title>

</head>
<body>
	<div class="header_wrapper">
    <div id="logo">
    <a href="/">
    <h2>E-shop</h2>
    </a>
    </div>
    <div class="search_field">
    <div>
    <form action="/search" method="post">
    <input class="search_tab" type="text" name="searchTab" placeholder="Find by name or in description">
       <input type="hidden" name="category" value="">
    <input type="hidden" name="priceMin" value="0">
    <input type="hidden" name="priceMax" value="0">
    <input type="submit" value="Search">
    </form></div>
    
    
    <div class="to_right search_tab">
      <form action="/search" method="get">
    <input type="submit" value="Advanced options">
    </form>
    </div>
    </div>
    
    
       <div>
   <br><br>
    	<c:choose>
				 <c:when test="${user_isLogged}">
<a class="icon" href="/profile">Profil</a>
       
    </c:when>    
    <c:otherwise>
      <a class="icon" href="/login">Log In</a>
      
    </c:otherwise>
    </c:choose>
    </div>
</div>
    <div id="menu">    
 
        <ul>
        <li><a href="/">Instruments</a></li>
        <li><a href="/faq">Clothes</a></li>
         <li><a href="/ryby">Vehicles</a></li>
        <li><a href="/zapytanie">For animals</a></li>
        <li><a href="/kontakt">Other</a></li>
        </ul>
    </div>
</body>
</html>