<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="gi" scope="session"
class="com.sourcecode.example.getItems"/>   
<%response.setContentType("application/json"); %>
<%response.getWriter().write(gi.items()); %>
