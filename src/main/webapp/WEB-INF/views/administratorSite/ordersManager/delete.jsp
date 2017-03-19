<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>
  <form method="get" action="deleteOrder">
  id :  <input type="text" name="id" value="" />
		<input align="top" type="submit" value="Delete" />
   </form> 
<form action="halo">
</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	<center>
			<c:forEach items="${orders}" var="order">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr><c:out value="id : ${order.id}"></c:out><br></tr>
			
			<tr><c:out value="price : ${order.price}" /><br></tr>
			
			<tr><c:out value="paymentMethod : ${order.paymentMethod}" /><br></tr>
			
			<tr><c:out value="shipping Address : ${order.shippingAddress}" /><br></tr>
			
			<tr><c:out value="billing Address : ${order.billingAddress}" /><br></tr>
			
			<tr><c:out value="couponCodes : ${order.couponCodes.code}" /></tr>
			
			<c:forEach items="${order.books}" var="book">
			<td><c:out value="${book.id} : ${book.name}" /><br></td>
			</c:forEach>
			</table>
			<form action="deleteOrder/${order.id}">
				<input align="bot" type="submit" value="Delete" />
  			 </form>
			</c:forEach>
		</center>
</body>
</html>
