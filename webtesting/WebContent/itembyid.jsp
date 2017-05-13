<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ii" scope="session"
class="com.sourcecode.example.getitembyidBean"/>
<%response.setContentType("application/json"); %>
<% response.getWriter().write(ii.getitem((String)request.getParameter("itemid")));%>    
    