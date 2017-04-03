<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Delete</title>
</head>
<body>
  <form method="get" action="deleteCouponCodes">
  id :  <input type="text" name="id" value="" />
		<input align="top" type="submit" value="Delete" />
   </form> 
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/couponCodes" method="get"></form>
		<input type="submit" value="Back" form="Back" />

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	<center>
			<c:forEach items="${couponCodes}" var="couponCode">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr>couponCode<c:out value="${couponCode.id}"></c:out><br></tr>
			
			<tr><c:out value="couponCode :${couponCode.code}" /><br><br></tr>
			
			 <form action="deleteCouponCodes/${couponCode.id}">
				<input align="top" type="submit" value="Delete" />
  			 </form>
			</table>
			</c:forEach>
		</center>
</body>
</html>
