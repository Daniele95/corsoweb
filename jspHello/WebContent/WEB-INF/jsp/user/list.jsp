<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jsp/common.jspf" %>
<%@include file="/WEB-INF/jsp/messages.jspf" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>OK</h1>
<div>
<!--  <a href="/jspHello/user"> New User </a> -->
<a href="${ctx}/user"> New User </a>
</div>

<%
	List<User> users = (List<User>)request.getAttribute("userDb");
%>


<%
	if (users != null && !users.isEmpty()) {
%>
<ol>
<%
	for (User user:users) {
		pageContext.setAttribute("user", user);
%>

<li>
	<!--   user.getId() nei bracket diventa user.id -->
	<a href="${ctx}/user?id=${user.id}"> 
		${user.firstName}&nbsp;${user.lastName}
	</a>
		(${user.age})
</li>
<%
	}
%>
</ol>
<%
	}else{
%>
<p>No users!</p>
<%
	}
%>
</body>
</html>