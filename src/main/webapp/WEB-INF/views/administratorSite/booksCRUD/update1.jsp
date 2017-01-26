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
						

        <form action="update">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Registration</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="hidden" name="id" value="<c:out value="${book.id}"/>"/>
						<c:out value="${book.id}"/></td>
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
					<tr>
                        <td>Category</td>
                        <td><input type="text" name="category" value="<c:out value="${book.price}"/>" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Update" /></td>
                    </tr>
            </table>
        </form>
				






	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
</body>
</html>