<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Delete</title>
</head>
<body>
 <form method="get" action="deleteCategory">
  Name :  <input type="text" name="categoryName" value="" />
		<input align="top" type="submit" value="Delete" />
   </form>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/categories" method="get"></form>
		<input type="submit" value="Back" form="Back" />

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	<center>
			<c:forEach items="${categories}" var="category">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr>Book id : <c:out value="${category.id}"></c:out><br></tr>
			
			<tr><c:out value="Description :${category.name}" /><br><br><br></tr>
			
			 <form action="deleteCategory/${category.id}">
				<input align="top" type="submit" value="Delete" />
  			 </form>
			</table>
			</c:forEach>
		</center>
</body>
</html>
