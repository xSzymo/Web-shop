<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/address" method="get"></form>

			<c:if test="${address != null}">
				<c:set var="user" scope="page" value="${address}"/>
			</c:if>
						

        <form action="update/update" method="post">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="text" name="id" value="<c:out value="${address.id}"/>"/></td>
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
                    <td>	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
		<input type="submit" value="Back" form="Back" /></td></tr>
            </table>
        </form>
				






	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>

	<center>
			<c:forEach items="${allAddress}" var="address">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr><c:out value="id : ${address.id}"></c:out><br></tr>
			
			<tr><c:out value="ulica : ${address.street}" /><br></tr>
			
			<tr><c:out value="kod pocztowy : ${address.postalCode}" /><br></tr>
			
			<tr><c:out value="miasto : ${address.city}" /><br></tr>
						
			<tr><c:out value="kraj : ${address.country}" /><br></tr>
			
			 <form action="update/${address.id}" method="GET">
				<br><input align="top" type="submit" value="Update" />
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
  			 </form>
			</table>
			</c:forEach>
	</center>
</body>
</html>