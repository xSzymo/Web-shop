<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>

			<c:if test="${book != null}">
				<c:set var="book" scope="page" value="${book}"/>
			</c:if>
						

        <form action="updateBook/update">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="text" name="id" value="<c:out value="${book.id}"/>"/></td>
                    </tr>
                    <tr>
                        <td>name</td>
                        <td><input type="text" name="name" value="<c:out value="${book.name}"/>" /></td>
                    </tr>
                        <td>author</td>
                        <td><input type="text" name="author" value="<c:out value="${book.author}"/>" /></td>
                    </tr>
					<tr>
                        <td>language</td>
                        <td><input type="text" name="language" value="<c:out value="${book.language}"/>" /></td>
                    </tr>
					<tr>
                        <td>price</td>
                        <td><input type="text" name="price" value="<c:out value="${book.price}"/>" /></td>
                    </tr>
					<tr>
                        <td>description</td>
                        <td><input type="text" name="description" value="<c:out value="${book.description}"/>" /></td>
                    </tr>
 					<c:forEach items="${categories}" var="category"  >
                      <tr>
					   	<td>  <input type="checkbox" name="<c:out value="${category.getName()}"/>" value="${category.getName()}"><c:out value="${category.getName()}"/></td>
					</tr>
					</c:forEach> 
                    <tr>
                        <td><input type="submit" value="Update" /></td>
                    </tr>
            </table>
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
			
			<tr><c:out value="Description :${book.description}" /><br><br></tr>
			
			
			<c:forEach items="${book.pictures}" var="picture">
			<IMG HEIGHT="100" WIDTH="100" SRC="/CRUD/getImage/${picture.name}">
			</c:forEach>
			
			 <form action="updateBook/${book.id}">
				<br><input align="top" type="submit" value="Update" />
  			 </form>
			</table>
			</c:forEach>
	</center>
</body>
</html>