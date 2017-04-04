<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Read</title>
</head>
<body>

  <form method="get" action="readOne">
  id :  <input type="text" name="id" value="" />
		<input align="top" type="submit" value="Find" />
   </form>
 <form method="get" action="read">
		<input align="top" type="submit" value="Read more" />
   </form>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/address" method="get"></form>
		<input type="submit" value="Back" form="Back" /><br><br>


<c:if test="${address != null}"> 
			<table border="0" width="13%">
			<tr><c:out value="id : ${address.getId()}"></c:out><br></tr>
			
			<tr><c:out value="ulica : ${address.street}" /><br></tr>
			
			<tr><c:out value="kod pocztowy : ${address.postalCode}" /><br></tr>
			
			<tr><c:out value="miasto : ${address.city}" /><br></tr>
						
			<tr><c:out value="kraj : ${address.country}" /><br></tr>
		</table>
</c:if> 





	<center>
			<c:forEach items="${allAddress}" var="address">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr><c:out value="id : ${address.id}"></c:out><br></tr>
			
			<tr><c:out value="ulica : ${address.street}" /><br></tr>
			
			<tr><c:out value="kod pocztowy : ${address.postalCode}" /><br></tr>
			
			<tr><c:out value="miasto : ${address.city}" /><br></tr>
						
			<tr><c:out value="kraj : ${address.country}" /><br></tr>
			
			
			</table>
			</c:forEach>
		</center>
		
</body>
</html>