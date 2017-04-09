<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>orders</title>
</head>
<body>
		<form id="Back" action="${sessionScope.PROJECT_NAME}account" method="get"></form>
		<input type="submit" value="Back" form="Back" />
	<center>
		<h2>New</h2>
			<c:forEach items="${orders}" var="order">
			<c:if test="${order.getIsRealized() == true}">
			<table border="1" width="40%">
			
				<tr><td>price </td><td> <c:out value="${order.price}" /><br></td></tr>
				
				<tr><td>paymentMethod </td><td><c:out value="${order.paymentMethod}" /></td></tr>
			
				<tr><td>shipping Address </td><td><c:out value="${order.shippingAddress}" /></td></tr>
			
				<tr><td>billing Address</td><td><c:out value="${order.billingAddress}" /></td></tr>
			
				<tr><td> couponCodes</td><td><c:out value="${order.couponCodes.code}" /></td></tr>
			
				 <tr><td> realized</td><td><c:out value="${order.getIsRealized()}" /></td></tr> 
				
				<c:forEach items="${order.books}" var="book">
					<tr><td>Book </td> <td><c:out value="${book.name}" /><br></td></tr>
				</c:forEach>
			</table>
			<br>
			</c:if>
			</c:forEach>
		</center>
			<br>
			<br>
			<br>
			<br>
			
				<center>
		<h2>Realized</h2>
			<c:forEach items="${orders}" var="order">
			<c:if test="${order.getIsRealized() == false}">
			<table border="1" width="40%">
			
				<tr><td>price </td><td> <c:out value="${order.price}" /><br></td></tr>
				
				<tr><td>paymentMethod </td><td><c:out value="${order.paymentMethod}" /></td></tr>
			
				<tr><td>shipping Address </td><td><c:out value="${order.shippingAddress}" /></td></tr>
			
				<tr><td>billing Address</td><td><c:out value="${order.billingAddress}" /></td></tr>
			
				<tr><td> couponCodes</td><td><c:out value="${order.couponCodes.code}" /></td></tr>
			
				 <tr><td> realized</td><td><c:out value="${order.getIsRealized()}" /></td></tr> 
				
				<c:forEach items="${order.books}" var="book">
					<tr><td>Book </td> <td><c:out value="${book.name}" /><br></td></tr>
				</c:forEach>
			</table>
			<br>
			</c:if>
			</c:forEach>
		</center>
</body>
</html>