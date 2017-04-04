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
			<c:if test="${user != null}">
				<c:set var="user" scope="page" value="${user}"/>
			</c:if>
						
        <form action="updateOne" method = "POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                        <td>id</td>
                        <td><input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
						 <c:out value="${user.id}"/></td> 
                    </tr>
                    <tr>
                        <td>login</td>
                        <td><input type="text" name="login" value="<c:out value="${user.getLogin()}"/>" /></td>
                    </tr>
                        <td>password</td>
                        <td><input type="text" name="password" value="<c:out value="${user.password}"/>" /></td>
                    </tr>
					<tr>
                        <td>name</td>
                        <td><input type="text" name="name" value="<c:out value="${user.name}"/>" /></td>
                    </tr>
					<tr>
                        <td>surname</td>
                        <td><input type="text" name="surname" value="<c:out value="${user.surname}"/>" /></td>
                    </tr>
					<tr>
                        <td>date of birth</td>
                        <td><input type="date" name="date" value="<c:out value="${user.age}"/>" /></td>
                    </tr>
					<tr>
                        <td>eMail</td>
                        <td><input type="text" name="eMail" value="<c:out value="${user.eMail}"/>" /></td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="hidden"/><c:out value="${address.getCountry()}"/></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><input type="hidden"/><c:out value="${address.getCity()}"/></td>
                    </tr>
                    <tr>
                        <td>Postal code</td>
                        <td><input type="hidden"/><c:out value="${address.getPostalCode()}"/></td>
                    </tr>
                    <tr>
                        <td>Street</td>
                        <td><input type="hidden"/><c:out value="${address.getStreet()}"/></td>
                    </tr>
			 		<tr>
                        <td>  <input type="checkbox" name="Admin" value="ROLE_ADMIN">admin<br></td>
                        <td>  <input type="checkbox" name="User" value="ROLE_USER" >user<br></td>
                        <input type="hidden" name="addressId" value="${address.getId()}" />
          			<td><input type="submit" value="Update" /></td>
                    </tr>
            </table>
        </form>



		<form action="${sessionScope.PROJECT_NAME}administratorSite/users/update">
    		<input type="submit" value="Back" />
		</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	
	 <form method="POST" action="createAddress?${_csrf.parameterName}=${_csrf.token}">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <td>street</td>
                        <td><input type="text" name="street" value="" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="postalCode" value="" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="city" value="" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="country" value="" /></td>
                    </tr>
                        <input type="hidden" name="userId" value="${user.getId()}" />
            </table>
                        <input type="hidden" name="addressId" value="${address.getId()}" />
			<br><input align="top" type="submit"  value="Save" /><br><br>	
</body>
</html>