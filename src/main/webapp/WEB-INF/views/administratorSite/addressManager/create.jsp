<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Create</title>
</head>
<body>
<center>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/address" method="get"></form>
		
<h2>Create book :</h2>
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
			
				<c:if test="${msgError != null}">
					<c:out value="${msgError}"></c:out><br>
				</c:if>
				
				<c:if test="${msgSuccess != null}">
					<c:out value="${msgSuccess}"></c:out><br>
				</c:if>
			
			<tr><td><input align="top" type="submit"  value="Save" /></td>
			<td>		
		<input type="submit" value="Back" form="Back" /></td></tr>
        </form>
         <table border="0" width="20%" cellpadding="3">
<tr><td>
</body>
</html>