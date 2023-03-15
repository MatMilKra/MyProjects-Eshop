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
<title>Rybki</title>

</head>
<body>
	
<%@include file="header.jsp" %>


    <div id="container">
    
     
        <div >Log in</div>
        <br>
        
        <form action="/login" method="post">
                <table class="center">
        
            <tr>
            <td>
                <label for="username">Username: </label>
                </td>
                <td>
                <input type="text" name="username" class="registerline" placeholder="">
                </td>
                </tr>
         
            <tr>
            <td>
                <label for="password">Password: </label>
                </td>
                <td>
                <input type="password" name="password" class="registerline" placeholder="">	
                </td>	
            </dv>
            
              </table>
              <br>
            <div class="loginbutton center inl">
                <input type="submit" class="registerbtn" value="Log in">
            </div>
            
        </form>
        
      <br>
        <div >
            <a href="/register">Don't have an account? Register now!</a>
        </div>
   
   
   

</div>
  
  
 </div>
</body>
</html>
