<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% System.out.println("Hello World!"); %>

<h2>Hello, <%= request.getAttribute("user") %></h2>
	<a href="<%=  response.encodeUrl("/jspHello/hello")%>" >ricarica pagina </a><br/>
	(<%=  response.encodeUrl("/jspHello/hello")%>)
</body>
</html>