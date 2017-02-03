<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>
 <form method="get" action="deleteBook">
  Name :  <input type="text" name="bookName" value="" />
		<input align="top" type="submit" value="Delete" />
   </form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	<center>
			<c:forEach items="${books}" var="book">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr>Book id : <c:out value="${book.id}"></c:out><br></tr>
			
			<tr><c:out value="Name : ${book.name}" /><br></tr>
			
			<tr><c:out value="Author :${book.author}" /><br></tr>
			
			<tr><c:out value="Language :${book.language}" /><br></tr>
			
			<tr><c:out value="Price :${book.price}" /><br></tr>
			
			<tr><c:out value="Description :${book.description}" /><br><br><br></tr>
			
			
			<c:forEach items="${book.pictures}" var="picture">
			<IMG HEIGHT="100" WIDTH="100" SRC="/CRUD/getImage/${picture.name}">
			</c:forEach>
			
			 <form action="deleteBook/${book.id}">
				<input align="top" type="submit" value="Delete" />
  			 </form>
			</table>
			</c:forEach>
		</center>
</body>
</html>