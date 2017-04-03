<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Read</title>
</head>
<body>

  <form method="get" action="readOne">
  id :  <input type="text" name="id" value="" />
		<input align="top" type="submit" value="Find" />
   </form>
 <form method="get" action="read">
		<input align="top" type="submit" value="Read more" />
   </form>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/orders" method="get"></form>
		<input type="submit" value="Back" form="Back" />

<c:if test="${order != null}"> 
			<table border="0" width="13%">
			<tr><c:out value="1.id : ${order.id}"></c:out><br></tr>
			
			<tr><c:out value="2.price : ${order.price}" /><br></tr>
			
			<tr><c:out value="3.paymentMethod : ${order.paymentMethod}" /><br></tr>
			
			<tr><c:out value="4.shippingAddressId : ${order.shippingAddress}" /><br></tr>
			
			<tr><c:out value="5.billingAddressId : ${order.billingAddress}" /><br></tr>
			
			<tr><c:out value="6.couponCodes : ${order.couponCodes.code}" /></tr>
			
			<c:forEach items="${order.books}" var="book">
			<td><c:out value="${book}" /><br></td>
			</c:forEach>
			
		</table>
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
			</c:forEach>
		</center>
		
</body>
</html>