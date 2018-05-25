<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<title>Eccezione!</title>
</head>
<body>
<%
	// exception è un oggetto implicito che fa riferimento
	// a eccezioni sollevate in altre pagine
	if( exception instanceof ClassNotFoundException ) {
%>
<h3>Driver non trovato!</h3>
<p><%= exception.getMessage() %></p>
<% 
	} else if( exception instanceof SQLException ) {
%>
<h3>Eccezione SQL</h3>
<p><%= exception.getMessage() %></p>
<p><%= ((SQLException)exception).getErrorCode() %></p>
<% 
	} else {
%>
<h3>Eccezione non prevista!</h3>
<p><%= exception.getMessage() %></p>
<% 
	}
%>
</body>
</html>