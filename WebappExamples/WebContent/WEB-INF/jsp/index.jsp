<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello JSP</title>
</head>
<body>
<h1>Hello JSP</h1>
<h2>Hello, <%= request.getAttribute("user") %></h2>
<p><a href="<%= response.encodeURL("/WebappExamples/hello") %>">Ricarica pagina</a><br/>
(<%= response.encodeURL("/WebappExamples/hello") %>)
</p> 
</body>
</html>