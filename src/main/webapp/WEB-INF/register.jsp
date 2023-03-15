<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Edu+VIC+WA+NT+Beginner:wght@400;600;700&family=Nunito:wght@300;500;700;800&family=Sono:wght@200;300;400;500;600;700;800&display=swap" rel="stylesheet">
<style><%@include file="/WEB-INF/css/style.css"%></style>
<title>Register</title>
</head>
<body>

<%@include file="header.jsp" %>
    <div id="container">
    
          
        <div >
        <div >Do you have an account already? <a  href="/login">Log in here</a>
       <br>
        or fill fields to register:</div></div>
        <br>
        
        <form action="/register" method="post">
                <table class="center">
        
           
            <tr>
            <td>
                <label for="username">Username: </label>
                </td>
                <td>
					<input type="text" id="user" name="username"  autocomplete="off" required /></div>
                </td>
                </tr>
         
            <tr>
            <td>
                <label for="password">Password: </label>
                </td>
                <td>
					<input type="password" id="password" name="password" placeholder="" value="" autocomplete="off" required /></div>
                </td>	
                 </tr>
 
            <tr>
            <td>
                <label for="password">Confirm password: </label>
                </td>
                <td>
					<input type="password" id="password" name="passwordConfirmed" placeholder="" value="" autocomplete="off" required /> <br /> </div>
                </td>	
                 </tr>

            <tr>
            <td>
                <label for="firstname">First name: </label>
                </td>
                <td>
					<input type="text" id="first" name="firstName" placeholder="" value="" autocomplete="off" required /></div>
                </td>
                 </tr>	

            <tr>
            <td>
                <label for="lastname">Last name: </label>
                </td>
                <td>
					<input type="text" id="last" name="lastName" placeholder="" value="" autocomplete="off" required /></div>
                </td>	
                 </tr>
  
            <tr>
            <td>
                <label for="email">E-mail: </label>
                </td>
                <td>
				 	<input type="email" id="email" pattern="[^@\\\\\\\\\s]+@[^@\s]+\.[^@\s]+" name="email" placeholder="" value="" autocomplete="off" required /></div>
                </td>
                 </tr>	
    
            <tr>
            <td>
                <label for="telephone">Telephone: </label>
                </td>
                <td>
					<input type="text" id="PhoneNumber" name="PhoneNumber" placeholder="" value="" autocomplete="off" required /></div>
                </td>
                 </tr>	
   
            
              </table>
              <br>
            <div class="center">
                <input type="submit" value="Register">
                
                </tr>
            </div>
        </form>
      <br>
</div>
  

  
 </div>

</body>
</html>