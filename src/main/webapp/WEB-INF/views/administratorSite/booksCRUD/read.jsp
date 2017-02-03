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
  Name :  <input type="text" name="name" value="" />
		<input align="top" type="submit" value="Find" />
   </form>
 <form method="get" action="read">
		<input align="top" type="submit" value="Back" />
   </form>

<c:if test="${book != null}"> 
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
</c:if>





<c:if test="${book == null}">
	<center>
			
			<c:forEach items="${books}" var="book">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr>Book id : <c:out value="${book.id}"></c:out><br></tr>
			
			<tr><c:out value="Name : ${book.name}" /><br></tr>
			
			<tr><c:out value="Author :${book.author}" /><br></tr>
			
			<tr><c:out value="Language :${book.language}" /><br></tr>
			
			<tr><c:out value="Price :${book.price}" /><br></tr>
			
			<c:forEach items="${categories}" var="category">
				<c:forEach items="${category.getBooks()}" var="books">
					<c:if test="${books.id == book.id}">
						<tr><c:out value="Category :${category.name}" /><br></tr>
					</c:if>
				</c:forEach>
			</c:forEach>
			
			<tr><c:out value="Description :${book.description}" /><br><br><br></tr>
			
			<c:forEach items="${book.pictures}" var="picture">
			<IMG HEIGHT="100" WIDTH="100" SRC="/CRUD/getImage/${picture.name}">
			</c:forEach>
			
			</table>
			</c:forEach>
			</c:if>
		</center>
		
</body>
</html>