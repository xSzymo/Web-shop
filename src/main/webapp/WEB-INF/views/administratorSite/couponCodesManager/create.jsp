<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Create</title>
</head>
<body>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/couponCodes" method="get"></form>
		<input type="submit" value="Back" form="Back" />
<center>


<h2>Create book :</h2>
		        <form method="get" action="createCouponCode">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <td>code</td>
                        <td><input type="text" name="code" value="" /></td>
                    </tr>
                    <tr>
                        <td>code Discount</td>
                        <td><input type="text" name="codeDiscount" value="" /></td>
                    </tr>
            </table>
			
				<c:if test="${msgError != null}">
					<c:out value="${msgError}"></c:out><br>
				</c:if>
				
				<c:if test="${msgSuccess != null}">
					<c:out value="${msgSuccess}"></c:out><br>
				</c:if>
			
			<br><input align="top" type="submit"  value="Save" /><br><br>
        </form>
         <table border="0" width="20%" cellpadding="3">
<tr><td>


	
</body>
</html>