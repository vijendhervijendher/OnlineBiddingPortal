<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="head" scope="session"
class="com.sourcecode.example.header"/>
<jsp:useBean id="uploadBean" scope="session"
class="com.sourcecode.example.uploadBean"/> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="uploaditem.css">

<title>Upload Item</title>
</head>
<body>
<%if(request.getMethod().equalsIgnoreCase("get")) {%>
	<h1 align="center">Upload Your Item Here..!</h1>
	
	<form method="post"
		action="UploadItem.jsp" >

		<!-- <table borderColor=#056796 cellSpacing=5 cellPadding=5 border=1px solid #f1f1f1> -->
		<div class="absolute">
			<label>Product Name:</label> <input type="text"
				placeholder="Product Name" name=<%= head.pname_key %> required />
				<label>Item Base Price:</label> <input type="text"
				placeholder="Base Price" name=<%= head.Base_key %> required /><br>
			
			<label>Item Description</label> <input
				type="text" name=<%=head.Descriptio_key %> required /> 
			<br>
			<button type="submit" value="Signup!">SUBMIT</button>

		</div>

	</form>    
<%}else if(request.getMethod().equalsIgnoreCase("post")) {%>
	<% 
	System.out.println((String)session.getAttribute(head.Username_key)+ request.getParameter(head.pname_key)+ 
			request.getParameter(head.Descriptio_key) + request.getParameter(head.Base_key));
		uploadBean.upload((String)session.getAttribute(head.Username_key), request.getParameter(head.pname_key), 
				request.getParameter(head.Descriptio_key), request.getParameter(head.Base_key));
		response.sendRedirect("main.jsp");
	%>
<%} %>
</body>
</html>