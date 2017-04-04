<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web shop</title>
</head>
<body>
<c:choose>
	<c:when test="${logged == false || logged == null}">
		<form id="saveForm" action="login" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
		</form>
		<form id="deleteForm" action="registration" method="get"></form>

		<input type="text" name="login" form="saveForm" value="login" />
		<br>
		<input type="password" name="password"  form="saveForm"
			value="password" />

		<input type="submit" name="save" value="Login" form="saveForm" />&nbsp;
		<input type="submit" name="delete" value="Register" form="deleteForm" />
		<br>
		<a href="login">Go to login page</a>
	</c:when>

	<c:when test="${logged == true}">
		<form id="goToAccount" action="${sessionScope.PROJECT_NAME}account" method="get"></form>
		<input type="submit" value="go to account" form="goToAccount" />&nbsp;
	</c:when>
</c:choose>

<br><br>
		<form id="Back" action="${sessionScope.PROJECT_NAME}" method="get"></form>
		<input type="submit" value="Back" form="Back" />

<div align="right">
<a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}shop/basket"><h1>Basket</h1></a>
</div>

	<br><br><br><br>
			<table border="0" width="6%">
			<tr><td>Categories : </td></tr>			
		<c:forEach items="${categories}" var="category">
				 <form action="shop/${category.getName()}">
					<tr><td><input align="top" type="submit" value="${category.getName()}" /></td></tr>
  				 </form>
		</c:forEach>
			</table>
</body>
</html>