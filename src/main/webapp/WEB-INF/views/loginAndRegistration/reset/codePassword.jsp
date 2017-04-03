<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>codePassword</title>
</head>
<body>
		<form id="Back" action="${sessionScope.PROJECT_NAME}shop" method="get"></form>
		
			<center><h2>Code from e-mail</h2>				         
				<form method="get" action="resetPassword">
				 <br> <input type="text" name="code" value="input code" /><br>
					<br> <input align="right" type="submit" value="Send" />
					<br><br><input type="submit" value="Shop" form="Back" />
			</center> </form>
			

	<c:choose>
		<c:when test="${msg != null}"><center>
			<c:out value="${msg}"></c:out></center>
		</c:when>
	</c:choose>	 
</body>
</html>
