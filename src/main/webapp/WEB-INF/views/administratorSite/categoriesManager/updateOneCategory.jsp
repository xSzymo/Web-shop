<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update one</title>
</head>
<body>
<center>
			<c:if test="${book != null}">
				<c:set var="user" scope="page" value="${user}"/>
			</c:if>
						
        <form action="updateOne" method="POST">
            <table border="0" width="20%" cellpadding="3">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="hidden" name="id" value="<c:out value="${category.id}"/>"/><c:out value="${category.id}"/></td>
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



		<form action="${sessionScope.PROJECT_NAME}administratorSite/categories/update">
    		<input type="submit" value="Back" />
		</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	

	
	
</body>
</html>