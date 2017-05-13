<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sourcecode.example.header"%>

<jsp:useBean id="deletebean" scope="session"
	class="com.sourcecode.example.deletemyBidBean" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% HashMap<String,String> output = deletebean.delete(request.getParameter("itemid"), (String)session.getAttribute(header.Username_key));
	response.sendRedirect("bidssummary.jsp");	%>
</body>
</html>