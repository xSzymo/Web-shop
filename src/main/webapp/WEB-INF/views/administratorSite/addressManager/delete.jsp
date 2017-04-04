<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Delete</title>
</head>
<body>
  <form method="post" action="delete">
  id :  <input type="text" name="id" value="" />
		<input align="top" type="submit" value="Delete" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
   </form> 
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/address" method="get"></form>
		<input type="submit" value="Back" form="Back" />

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	<center>
			<c:forEach items="${address}" var="address">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr><c:out value="id : ${address.id}"></c:out><br></tr>
			
			<tr><c:out value="ulica : ${address.street}" /><br></tr>
			
			<tr><c:out value="kod pocztowy : ${address.postalCode}" /><br></tr>
			
			<tr><c:out value="miasto : ${address.city}" /><br></tr>
						
			<tr><c:out value="kraj : ${address.country}" /><br></tr>
			 <form action="delete/${address.id}" method="post">
				<input align="top" type="submit" value="Delete" />
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
  			 </form>
			</table>
			</c:forEach>
		</center>
</body>
</html>
