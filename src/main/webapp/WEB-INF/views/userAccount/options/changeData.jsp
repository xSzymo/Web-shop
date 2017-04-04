<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>change data</title>
</head>

<center>
    <form action="update" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>login</td>
                        <td><input type="hidden" name="login" value="<c:out value="${user.getLogin()}"/>" /><c:out value="${user.getLogin()}"/></td>
                    </tr>
                        <td>password</td>
                        <td><a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}account/changePasswd?">Click here</a></td>
                    </tr>
					<tr>
                        <td>name</td>
                        <td><input type="text" name="name" value="<c:out value="${user.getName()}"/>" /></td>
                    </tr>
					<tr>
                        <td>surname</td>
                        <td><input type="text" name="surname" value="<c:out value="${user.getSurname()}"/>" /></td>
                    </tr>
					<tr>
                        <td>age</td>
                        <td><input type="text" name="age" value="<c:out value="${user.getAge()}"/>" /></td>
                    </tr>
					<tr>
                        <td>eMail</td>
                        <td><a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}account/changeEmail?">Click here</a></td>
                    </tr>
                        <td>Country</td>
                        <td><input type="text" name="country" value="<c:out value="${address.getCountry()}"/>"/></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><input type="text" name="city"value="<c:out value="${address.getCity()}"/>"/></td>
                    </tr>
                    <tr>
                        <td>Postal code</td>
                        <td><input type="text" name="postalCode" value="<c:out value="${address.getPostalCode()}"/>"/></td>
                    </tr>
                    <tr>
                        <td>Street</td>
                        <td><input type="text" name="street"value="<c:out value="${address.getStreet()}"/>"/></td>
                    </tr>
				<input type="hidden" name="addressId" value="<c:out value="${address.getId()}"/>"/>
			 		<tr>
          			<td><input type="submit" value="Update" /></td>
                    </tr>
            </table>
        </form>


		<form action="${sessionScope.PROJECT_NAME}account">
    		<input type="submit" value="Back" />
		</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	
	 <form method="POST" action="createAddress">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
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
			<br><input align="top" type="submit"  value="Save" /><br><br>

</body>
</html>
