<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRUD</title>
</head>
<body>
<center>123
        <form method="post" action="userRegistration">
            <table border="1" width="20%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2">Registration</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Login</td>
                        <td><input type="text" name="login" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" name="password" value="" /></td>
                    </tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="optional" /></td>
                    </tr>
					<tr>
                        <td>Surname</td>
                        <td><input type="text" name="surname" value="optional" /></td>
                    </tr>
					<tr>
                        <td>E-mail</td>
                        <td><input type="text" name="eMail" value="" /></td>
                    </tr>
					<tr>
                        <td>Date birth</td>
                        <td><input type="date" name="dateBirth" value="optional" /></td>
                    </tr>
					<tr>
                        <td>Country </td>
                        <td><input type="text" name="country" value="optional" /></td>
                    </tr>
					<tr>
                        <td>City </td>
                        <td><input type="text" name="city" value="optional" /></td>
                    </tr>
					<tr>
                        <td>Postal code </td>
                        <td><input type="text" name="postalCode" value="optional" /></td>
                    </tr>
					<tr>
                        <td>street</td>
                        <td><input type="text" name="street" value="optional" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Register" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>

			<c:choose>
				<c:when test="${msg != null}"><center>
					<c:out value="${msg}"></c:out></center>
					</c:when>
				</c:choose>

</body>
</html>

