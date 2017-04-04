<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Read</title>
</head>
<body>


 <form action="read" method="post">
  Name :  <input type="text" name="name" value="" />
		<input align="top" type="submit" value="Find" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
   </form>
 <form method="get" action="read">
		<input align="top" type="submit" value="Read more" />
   </form>

		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/books" method="get"></form>
		<input type="submit" value="Back" form="Back" /><br><br>



<c:if test="${book != null}">
			--------------------------------------------<br>
			<tr>Book id : <c:out value="${book.id}"></c:out><br></tr>
			
			<tr><c:out value="Name : ${book.name}" /><br></tr>
			
			<tr><c:out value="Author :${book.author}" /><br></tr>
			
			<tr><c:out value="Language :${book.language}" /><br></tr>
			
			<tr><c:out value="Price :${book.price}" /><br></tr>
			
			<tr><c:out value="Price :${category.getName()}" /><br></tr>
			
			<tr><c:out value="Description :${book.description}" /><br><br><br></tr>
			
			
			<c:forEach items="${book.pictures}" var="picture">
			<IMG HEIGHT="100" WIDTH="100" SRC="${sessionScope.PROJECT_NAME}getImage/${picture.name}">
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
			<IMG HEIGHT="100" WIDTH="100" SRC="${sessionScope.PROJECT_NAME}getImage/${picture.name}">
			</c:forEach>
			
			</table>
			</c:forEach>
			</c:if>
		</center>
		
</body>
</html>