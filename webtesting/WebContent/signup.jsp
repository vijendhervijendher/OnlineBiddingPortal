<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.HashMap"%>
<jsp:useBean id="sb" scope="session"
	class="com.sourcecode.example.signupBean" /><jsp:useBean id="head" scope="session"
class="com.sourcecode.example.header"/> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="signup.css">
<title>SignUp here</title>
<!-- Bootstrap core CSS -->
<link href="https://getbootstrap.com//dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="https://getbootstrap.com//assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="https://getbootstrap.com/examples/dashboard/dashboard.css"
	rel="stylesheet">

<script
	src="https://getbootstrap.com//assets/js/ie-emulation-modes-warning.js"
	type="text/javascript"></script>

<style>
	body{
	background-position: center 0px; 
	height: 600px; 
	background-image: url("http://www.acertarconsultoria.com/wp-content/uploads/2015/04/bombillos-1024x576.jpg"); 
	background-size: 1920px auto;
	}
	
	h2,p, h3,label{
	color: black;
	}
	</style>
	
	<script>
	var password = document.getElementById("password")	  , cpassword = document.getElementById("confirm_password");

	function validatePassword(){
	  if(password.value != cpassword.value) {
	    confirm_password.setCustomValidity("Passwords Don't Match");
	  } else {
	    confirm_password.setCustomValidity('');
	  }
	}

	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;
	</script>
</head>
<body>
	<h1 align="center">SignUp here!</h1>
	<%
		if (request.getMethod().equalsIgnoreCase("get")) {
	%>
	<form method="post"
		action="signup.jsp" >

		<!-- <table borderColor=#056796 cellSpacing=5 cellPadding=5 border=1px solid #f1f1f1> -->
		<div class="absolute">
			<label>First Name:</label> 
			<input type="text" placeholder="First Name" name=<%= "\""+head.Fname_key+"\"" %> required />
			
			<label>Last Name:</label> 
			<input type="text" placeholder="Last Name" name=<%= "\""+head.Lname_key+"\"" %> required /> 
			
			<label>Username:</label> 
			<input type="text" placeholder="Enter Username" name=<%= "\""+head.Username_key+"\"" %> required /> 
			
			<label>Email:</label>
			<input type="text" name=<%= "\""+head.email_key+"\"" %> required /> 
			
			<label>Phone:</label> 
			<input type="number" name=<%= "\""+head.Phone_key+"\"" %> required /> 
			
			<label>Password:</label> 
			<input type="password" name=<%= "\""+head.Password_key+"\"" %> required /> 
			
			<label>Confirm Password:</label>
			<input type="password" name="cpassword"  required /> 
			
			<label>Address</label> 
			<input type="text" name=<%= "\""+head.Street_key+"\"" %> required /> 
			
			<label>City:</label> 
			<input type="text" name=<%= "\""+head.City_key+"\"" %> required /> 
			
			<label>State </label> 
			<input type="text" name=<%= "\""+head.State_key+"\"" %> required/> 
			
			<label>Zip:</label> 
			<input type="text" name=<%= "\""+head.Zip_key+"\"" %> required />
			
			<br>
			<button type="submit" value="Signup!">SUBMIT</button>

		</div>

	</form>
	<%if (session.getAttribute("signuperror") != null) {%>
	<script type="text/javascript">
		document.getElementById("loginError").innerHTML = "There some error@ Please try after some time.";
	</script>
	<% session.removeAttribute("signuperror");%>
	<%}%>
	<%}else if(request.getMethod().equalsIgnoreCase("post")) {%>

        <% sb.setfname((String)request.getParameter(head.Fname_key));%>
        <% sb.setlname((String)request.getParameter(head.Lname_key));%>
        <% sb.setemail((String)request.getParameter(head.email_key));%>
        <% sb.setusername((String)request.getParameter(head.Username_key));%>
        <% sb.setpassword((String)request.getParameter(head.Password_key));%>
        <% sb.setstreet((String)request.getParameter(head.Street_key));%>
        <% sb.setcity((String)request.getParameter(head.City_key));%>
        <% sb.setstate((String)request.getParameter(head.State_key));%>
        <% sb.setzip((String)request.getParameter(head.Zip_key));%>
        <% sb.setphone((String)request.getParameter(head.Phone_key));%>
        <% HashMap<String, String> output =  sb.signup(); %>
        <% if(output.get(head.Result_key).equals(head.Success)){%>
             <%response.sendRedirect("login.jsp");%>
        <%}else {%>
            <%session.setAttribute("signuperror", output.get(head.error_key));%>
            <%response.sendRedirect("signup.jsp");%> 
        <% }%>      
              
<%} else{%>
       <%response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Action forbidden"); %>
<%} %>
</body>
</html>