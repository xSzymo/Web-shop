<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eMail</title>
</head>

<center>
    <form action="changeEmailCode">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>code</td>
                        <td><input type="text" name="code" value="" /></td>
                    </tr>
                        <td><input type="hidden" name="eMail" value='<c:out value="${eMail}"></c:out>' /></td>
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
</body>
</html>
