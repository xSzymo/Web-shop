<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Read</title>
</head>
<body>

  <form method="POST" action="read">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
  id :  <input type="text" name="id" value="" />
		<input align="top" type="submit" value="Find" />
   </form>
 <form method="get" action="read">
		<input align="top" type="submit" value="Read more" />
   </form>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/couponCodes" method="get"></form>
		<input type="submit" value="Back" form="Back" />
<br><br>
<c:if test="${couponCode != null}"> 
			<table border="0" width="13%">
			<tr><c:out value="id : ${couponCode.id}"></c:out><br></tr>
			
			<tr><c:out value="Discount : ${couponCode.codeDiscount}" /><br></tr>
			
			<tr><c:out value="code : ${couponCode.code}" /><br></tr>
		</table>
</c:if> 





	<center>
			<c:forEach items="${couponCodes}" var="coupondCode">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr><c:out value="id : ${coupondCode.id}"></c:out><br></tr>
			
			<tr><c:out value="Discount : ${coupondCode.codeDiscount}" /><br></tr>
			
			<tr><c:out value="code : ${coupondCode.code}" /><br></tr>
			
			</table>
			</c:forEach>
		</center>
		
</body>
</html>