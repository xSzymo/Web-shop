<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update one</title>
</head>
<body>
<center>
			<c:if test="${book != null}">
				<c:set var="user" scope="page" value="${user}"/>
			</c:if>
						
        <form action="updateOne" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="hidden" name="id" value="<c:out value="${couponCode.id}"/>"/><c:out value="${couponCode.id}"/></td>
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
                    </tr>
            </table>
        </form>



		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/couponCodes/update" method="get"></form>
		<input type="submit" value="Back" form="Back" />
		</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	

	
	
</body>
</html>