<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>


<center>

    <form action="changePassword">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
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



		<form action="${sessionScope.PROJECT_NAME}administratorSite/users/update">
    		<input type="submit" value="Back" />
		</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
</body>
</html>