<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/couponCodes" method="get"></form>

			<c:if test="${couponCode != null}">
				<c:set var="user" scope="page" value="${couponCode}"/>
			</c:if>
						

        <form action="update/updateOne" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="text" name="id" value="<c:out value="${couponCode.id}"/>"/></td>
                    </tr>
                    <tr>
                        <td>codeDiscount</td>
                        <td><input type="text" name="codeDiscount" value="<c:out value="${couponCode.codeDiscount}"/>" /></td>
                    </tr>
                    <tr>
                        <td>code</td>
                        <td><input type="text" name="code" value="<c:out value="${couponCode.code}"/>" /></td>
                    </tr>

                    <tr>				
                        <td><input type="submit" value="Update" /></td>
		<td><input type="submit" value="Back" form="Back" /></td>                    
</tr>
            </table>
        </form>
				




	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>

	<center>
			<c:forEach items="${couponCodes}" var="couponCode">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr>Category id : <c:out value="${couponCode.id}"></c:out><br></tr>
			
			<tr><c:out value="login : ${couponCode.codeDiscount}" /><br></tr>
			
			<tr><c:out value="login : ${couponCode.code}" /><br></tr>
			
			 <form action="update/${couponCode.id}">
				<br><input align="top" type="submit" value="Update" />
  			 </form>
			</table>
			</c:forEach>
	</center>
</body>
</html>