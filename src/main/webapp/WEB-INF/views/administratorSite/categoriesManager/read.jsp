<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Read</title>
</head>
<body>

  <form method="POST" action="read">
  Name :  <input type="text" name="name" value="" />
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
		<input align="top" type="submit" value="Find" />
   </form>
 <form method="get" action="read">
		<input align="top" type="submit" value="Read more" />
   </form>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/categories" method="get"></form>
		<input type="submit" value="Back" form="Back" />
		<br><br>

<c:if test="${category != null}"> 
			<table border="0" width="13%">
			<tr><c:out value="id : ${category.id}"></c:out><br></tr>
			
			<tr><c:out value="Name : ${category.name}" /><br></tr>
			<%-- 
			Click to see books 
			<tr><c:out value="Password : ${category.password}" /><br></tr> --%>
		</table>
</c:if> 





	<center>
			<c:forEach items="${categories}" var="category">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr><c:out value="id : ${category.id}"></c:out><br></tr>
			
			<tr><c:out value="Name : ${category.name}" /><br></tr>
			<%-- 
			Click to see books 
			<tr><c:out value="Password : ${category.password}" /><br></tr> 
			--%>
			
			</table>
			</c:forEach>
		</center>
		
</body>
</html>