<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>
  <form method="get" action="deleteAddress">
  id :  <input type="text" name="id" value="" />
		<input align="top" type="submit" value="Delete" />
   </form> 

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
			 <form action="deleteAddress/${address.id}">
				<input align="top" type="submit" value="Delete" />
  			 </form>
			</table>
			</c:forEach>
		</center>
</body>
</html>
