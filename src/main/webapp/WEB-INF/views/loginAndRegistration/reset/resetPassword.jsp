<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRUD</title>
</head>
<body>
	
	<center>
		        
		<c:choose>
			<c:when test="${codeAccepted == true}">
		<form method="get" action="reset">
			     <h2>Reset password</h2>
			<input type="text" name="password"
				value="new password" /> <br><br>
			<input align="top" type="submit" value="Accept" /><br>
			<br> <a href="forgotPassword"><font size="2">Back</font></a><br>
		</form>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${msg != null}">
				<center>
				<c:if test="${msg.equals(Success)}">
				<c:out value="${msg}"></c:out><br>
					<a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}"><font size="3">Back</font>
					</c:if>
					<c:if test="${!msg.equals(Success)}">
					<c:out value="${msg}"></c:out><br>
					<a href="codePassword"><font size="3">Back</font>
					</a></c:if>
				</center>
			</c:when>
		</c:choose>
</body>
</html>
