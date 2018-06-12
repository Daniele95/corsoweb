<%@page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user editing</title>
</head>
<body>
<form action="${ctx}/user" method="POST">
	<input type="hidden" name="id" value="${user.id}" />
	<div>
		<label for="firstName">First name: </label>
		<input type="text" id="firstName" name="firstName" value="${user.firstName}"/>
		<!-- user.firstName sta per ((User)request.getAttribute("user")).getFirstName()
		è detto expression language -->
	</div>
	<div>
		<label for="lastName">Last name: </label>
		<input type="text" id="lastName" name="lastName" value="${user.lastName}"/>
	</div>
	<div>
		<label for="age">Age: </label>
		<input type="number" id="age" name="age" value="${user.age}"/>
	</div>
	<input type="submit" value="Save"/>
</form>

</body>
</html>