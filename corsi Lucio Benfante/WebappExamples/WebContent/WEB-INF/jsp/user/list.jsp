<%@page import="com.corsojava.webapp.model.User"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jsp/common.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of users</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/messages.jspf" %>
<div>
<a href="${ctx}/user">New user</a>
</div>
<%
	List<User> users = (List<User>)request.getAttribute("users");
%>
<%
	if (users != null && !users.isEmpty()) {
%>
<ol>
<%
	for(User user: users) {
		pageContext.setAttribute("user", user);
%>
	<li>
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
	} else {
%>
<p>No users!</p>
<%
	}
%>
</body>
</html>