<%@page import="com.corsojava.webapp.model.User"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jsp/common.jspf" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<c:if test="${ !empty users }">
	
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
	
</c:if>
<c:if test="${ empty users }">
	<p>No users!</p>
</c:if>


</body>
</html>