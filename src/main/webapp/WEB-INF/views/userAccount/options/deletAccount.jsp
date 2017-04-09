<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>orders</title>
</head>
<body>
		<form id="Back" action="${sessionScope.PROJECT_NAME}account" method="get"></form>
		<input type="submit" value="Back" form="Back" />
	<center>
		<h2>Delete account</h2>
		    <form action="deleteAccount" method = "POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <td>password</td>
                        <td><input type="text" name="password" value="" /> </td>
                    </tr>
                    <tr>
                        <td>repeat password</td>
                        <td><input type="text" name="password1" value="" /> </td>
                    </tr>
			 		<tr>
          			<td><input type="submit" value="send" /></td>
                    </tr>
            </table>
        </form>
		</center>
</body>
</html>