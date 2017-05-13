<%@page import="com.sourcecode.example.header"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="nb" scope="session"
class="com.sourcecode.example.loginBean"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="login.css">
<title>Login Page</title>
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
	
	</head>
<body>
<p id="loginError"></p>
<h1 align="center">Login Page</h1>
<%if(request.getMethod().equalsIgnoreCase("get")) {%>
   <form method="post"  action="login.jsp">
      <div class="container">  
         <label><b>Username:</b></label>
         <input type="text" placeholder="Enter Username" name="username" required>
         </input>
         <label><b>Password:</b></label>
         <input type="password" placeholder="Enter Password" name="password"></input>
         <button type="submit">Login</button>
			<input type="checkbox" checked="checked"> Remember me
		</div>
		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="cancelbtn">Cancel</button>
			<span class="psw"><a href="signup.jsp">New User?</a></span>
		</div>
   </form>
   <%if(session.getAttribute("error") != null) {%>
       <script>
          document.getElementById("loginError").innerHTML = "Wrong username or password.";
       </script>
       <%session.removeAttribute("error"); %>
   <% }%>    
<%}else if(request.getMethod().equalsIgnoreCase("post")) {%>
   <%if(nb.isValiduser((String)request.getParameter("username"), (String)request.getParameter("password"))) {%>
       <% session.setAttribute("auth", 1); %>
       <% session.setAttribute(header.Username_key, (String)request.getParameter("username"));%>
       <% session.setMaxInactiveInterval(30*60); %>
       <%! Cookie cookie = new Cookie("authcookie","set"); %>>
       <% cookie.setMaxAge(30*60);%>
       <% response.addCookie(cookie); %>
       <% response.sendRedirect("main.jsp"); %> 
   <%} else{%>
       <%session.setAttribute("error", 1); %>
       <%response.sendRedirect("login.jsp"); %>
   <%} %>
<%} else{%>
       <%response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Action forbidden"); %>
<%} %>
</body>
</html>