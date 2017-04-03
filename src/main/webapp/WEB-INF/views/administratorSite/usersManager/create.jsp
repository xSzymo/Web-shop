<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Create</title>
</head>
<body>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/users" method="get"></form>
		<input type="submit" value="Back" form="Back" />
		
<center>


<h2>Create User :</h2>
		        <form method="get" action="createUser">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <td>login</td>
                        <td><input type="text" name="login" value="" /></td>
                    </tr>
                    <tr>
                        <td>password</td>
                        <td><input type="text" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td>name</td>
                        <td><input type="text" name="name" value="" /></td>
                    </tr>
                    <tr>
                        <td>surname</td>
                        <td><input type="text" name="surname" value="" /></td>
                    </tr>
                    <tr>
                        <td>eMail</td>
                        <td><input type="text" name="eMail" value="" /></td>
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
                        <td>  <input type="checkbox" name="User" value="ROLE_USER" checked="checked">user<br></td>
                    </tr>
                        <input type="hidden" name="address" value="${address.getId()}" />
            </table>
			
			
				<c:if test="${msgError != null}">
					<c:out value="${msgError}"></c:out><br>
				</c:if>
				
				<c:if test="${msgSuccess != null}">
					<c:out value="${msgSuccess}"></c:out><br>
				</c:if>
			
			<br><input align="top" type="submit"  value="Save" /><br><br>
        </form>




		        <form method="get" action="createAddress">
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
            </table>
			<br><input align="top" type="submit"  value="Save" /><br><br>
</body>
</html>