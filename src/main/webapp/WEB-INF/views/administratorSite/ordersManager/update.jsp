<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>

			<c:if test="${address != null}">
				<c:set var="user" scope="page" value="${address}"/>
			</c:if>
						

<%--          <form action="updateAddress/update">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="text" name="id" value="<c:out value="${address.id}"/>"/></td>
                    </tr>
                    <tr>
                        <td>codeDiscount</td>
                        <td><input type="text" name="street" value="<c:out value="${address.street}"/>" /></td>
                    </tr>
                    <tr>
                        <td>code</td>
                        <td><input type="text" name="postalCode" value="<c:out value="${address.postalCode}"/>" /></td>
                    </tr>
                    <tr>
                        <td>code</td>
                        <td><input type="text" name="city" value="<c:out value="${address.city}"/>" /></td>
                    </tr>
                    <tr>
                        <td>code</td>
                        <td><input type="text" name="country" value="<c:out value="${address.country}"/>" /></td>
                    </tr>
                    <tr>				
                        <td><input type="submit" value="Update" /></td>
                    </tr>
            </table>
        </form>  --%>
				






	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>

	<center>
			<c:forEach items="${orders}" var="order">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr><c:out value="ShippingAddress street : ${order.getShippingAddress().getStreet()}"></c:out><br></tr> 
			
			<tr><c:out value="ShippingAddress postal code : ${order.getShippingAddress().getPostalCode()}"></c:out><br></tr> 
			
			<tr><c:out value="ShippingAddress city : ${order.getShippingAddress().getCity()}"></c:out><br></tr> 
			
			<tr><c:out value="ShippingAddress country : ${order.getShippingAddress().getCountry()}"></c:out><br><br></tr> 
			
			
			<tr><c:out value="BillingAddress street : ${order.getBillingAddress().getStreet()}"></c:out><br></tr> 
			
			<tr><c:out value="BillingAddress postal code : ${order.getBillingAddress().getPostalCode()}"></c:out><br></tr> 
			
			<tr><c:out value="BillingAddress city : ${order.getBillingAddress().getCity()}"></c:out><br></tr> 
			
			<tr><c:out value="BillingAddress country : ${order.getBillingAddress().getCountry()}"></c:out><br><br></tr> 
			
			
			
			<tr><c:out value="CouponCodes code discount: ${order.getCouponCodes().getCodeDiscount()}"></c:out><br></tr> 
			
			<tr><c:out value="CouponCodes code : ${order.getCouponCodes().getCode()}"></c:out><br><br></tr> 
			
						
			<tr><c:out value="sRealized : ${order.getIsRealized()}"></c:out><br></tr> 
			
			<tr><c:out value="Price : ${order.getPrice()}"></c:out><br></tr> 
			
			<tr><c:out value="PaymentMethod : ${order.getPaymentMethod()}"></c:out><br></tr> 
			
			
			 <form action="updateOrder/${order.getId()}">
				<br><input align="top" type="submit" value="Update" />
  			 </form>
			</table>
			</c:forEach>
	</center>
</body>
</html>