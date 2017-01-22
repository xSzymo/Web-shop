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
 <center>
        <form method="get" action="userLogin">
            <table border="1" width="20%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2">Login</th>
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
                </tbody>
            </table>
			<br><input align="top" type="submit"  value="Login" /><br><br>

				<a href="forgotPassword"><font size="2">Forgot password</font></a><br>
				<a href="forgotUsername"><font size="2">Forgot username</font></a><br>
        </form>

			<c:choose>
				<c:when test="${msg != null}">
					<c:out value="${msg}"></c:out>
				</c:when>
				</c:choose>
				
			</center>
</body>
</html>
