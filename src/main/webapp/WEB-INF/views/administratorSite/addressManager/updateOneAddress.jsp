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
						
        <form action="update" method="post">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="hidden" name="id" value="<c:out value="${address.id}"/>"/><c:out value="${address.id}"/></td>
                    </tr>
                    <tr>
                        <td>street</td>
                        <td><input type="text" name="street" value="<c:out value="${address.street}"/>" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="postalCode" value="<c:out value="${address.postalCode}"/>" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="city" value="<c:out value="${address.city}"/>" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="country" value="<c:out value="${address.country}"/>" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Update" /></td>
                    </tr>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
            </table>
        </form>



		<form action="${sessionScope.PROJECT_NAME}administratorSite/address/update">
    		<input type="submit" value="Back" />
		</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	

	
	
</body>
</html>