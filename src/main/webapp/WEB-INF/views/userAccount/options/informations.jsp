<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>informations</title>
</head>
	<center>
	<h2>Account informations</h2>
			<table border="1" width="15%">			
			<tr><td>Login </td><td><c:out value="${user.login}" /></td></tr>
			
			<tr><td>Password </td><td><c:out value="${user.password}" /></td></tr>
			
			<tr><td>Name </td><td><c:out value="${user.name}" /></td></tr>
			
			<tr><td>Surname </td><td><c:out value="${user.surname}" /></td></tr>
			
			<tr><td>E-mail </td><td><c:out value=" ${user.eMail}" /></td></tr>
			
			<tr><td>Date of birth </td><td><c:out value="${user.age}" /></td></tr>
			
			<tr><td>Role </td><td><c:out value="${role.getRole()}" /></td></tr>
				</table><br>
						<form id="Back" action="${sessionScope.PROJECT_NAME}account" method="get"></form>
		<input type="submit" value="Back" form="Back" />
		</center>
</body>
</html>