<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>

			<c:if test="${user != null}">
				<c:set var="user" scope="page" value="${user}"/>
			</c:if>
						

        <form action="updateCategory/update">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="text" name="id" value="<c:out value="${category.id}"/>"/></td>
                    </tr>
                    <tr>
                        <td>name</td>
                        <td><input type="text" name="name" value="<c:out value="${category.name}"/>" /></td>
                    </tr>

                    <tr>				
                        <td><input type="submit" value="Update" /></td>
                    </tr>
            </table>
        </form>
				






	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>

	<center>
			<c:forEach items="${categories}" var="category">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr>Category id : <c:out value="${category.id}"></c:out><br></tr>
			
			<tr><c:out value="login : ${category.name}" /><br></tr>
			
			 <form action="updateCategory/${category.id}">
				<br><input align="top" type="submit" value="Update" />
  			 </form>
			</table>
			</c:forEach>
	</center>
</body>
</html>