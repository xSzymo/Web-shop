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
			
		<h2>Create order :</h2>

		<form method="POST" action="updateOne">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
			<table border="0" width="25%">
				<tr>
				<c:forEach items="${payments}" var="payment">
				<c:if test="${payment.equals(orderPayment)}">
					<td><input type="radio" name="payment" value="${payment}" checked="checked">${payment}</input></td>
				</c:if>
				 <c:if test="${!payment.equals(orderPayment)}">
					<td><input type="radio" name="payment" value="${payment}">${payment}</input></td>
				</c:if> 
				</c:forEach>
				</tr><tr>

				                         <td>price</td>
                        <td><input type="text" name="price" value="${order.getPrice()}"/></td>
                    </tr>
			<tr><td></td></tr>
			  	  <tr>
                       <td>shipping address</td>
                    </tr>
				 <tr>
                        <td>street</td>
                        <td><input type="text" name="shippingAddressStreet" value="${order.getShippingAddress().getStreet()}" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="shippingAddressPostalCode" value="${order.getShippingAddress().getPostalCode()}" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="shippingAddressCity" value="${order.getShippingAddress().getCity()}" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="shippingAddressCountry" value="${order.getShippingAddress().getCountry()}" /></td>
                    </tr>

				<tr><td></td></tr>
                    <tr>
                        <td>billing address</td>

                    </tr>
				 <tr>
                        <td>street</td>
                        <td><input type="text" name="billingAddressStreet" value="${order.getBillingAddress().getStreet()}" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="billingAddressPostalCode" value="${order.getBillingAddress().getPostalCode()}" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="billingAddressCity" value="${order.getBillingAddress().getCity()}" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="billingAddressCountry" value="${order.getBillingAddress().getCountry()}" /></td>
                    </tr>
			<tr><td></td></tr>
                    <tr>
                        <td>CouponCode</td>
                    </tr>

                    <tr>
                        <td>code discount</td>
                        <td><input type="text" name="couponCodeDiscount" value="${order.getCouponCodes().getCodeDiscount()}" /></td>
                    </tr>
                    <tr>
                        <td>code</td>
                        <td><input type="text" name="couponCode" value="${order.getCouponCodes().getCode()}" /></td>
                    </tr>
				<br>
				<br>
				<tr><td>
				
			<tr><td></td></tr>
                    <tr>
                        <td>Books</td>
					<c:if test="${order.getBooks() == null}">
					<td>choose books</c:if>
                    </tr>
			 <c:forEach items="${order.getBooks()}" var="book">
					<tr><td>${book.getName()}</td></tr>
				</c:forEach>
			
			<tr><td></td></tr>
				<tr>
					<td>Relized
					<c:if test="${order.getIsRealized() == true}">
					<td><input type="radio" name="realized" value="${true}" checked="checked">yes</input></td>	
					<td><input type="radio" name="realized" value="${false}">no	</input></td>			
					</c:if>
					
					<c:if test="${order.getIsRealized() == false}">
					<td><input type="radio" name="realized" value="${true}">yes	</input></td>
					<td><input type="radio" name="realized" value="${false}" checked="checked">no</input></td>		
					</c:if>
				</tr>
				<tr>
				<td><input type="submit" value="send" />
				</td></tr>
         <input type="hidden" name="orderId" value="${order.getId()}" />
        <c:forEach items="${books}" var="book">
         <input type="hidden" name="books" value="${book}" />
		</c:forEach>
			</table>
		</form>



		<form action="${sessionScope.PROJECT_NAME}administratorSite/orders/update">
    		<input type="submit" value="Back" />
		</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	
	
	
	
	 <form method="POST" action="createBooks">
	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
            <table border="0" width="20%" cellpadding="3">
<%--          <c:forEach items="${order.getBooks()}" var="book">
					<tr><td><input type="checkbox" name="${book.getName()}" value="${book.getName()}" checked="checked">${book.getName()}</input></td></tr>
		</c:forEach> --%>

        <c:forEach items="${books}" var="book">
			<c:set var="HALO" scope="session" value="${false}"/>
        	 <c:forEach items="${order.getBooks()}" var="orderBook">
					<c:if test="${orderBook.getId() == book.getId()}">
						<c:set var="HALO" scope="session" value="${true}"/>
						<break>
					</c:if>
				</c:forEach>	
				
				<c:if test="${HALO == false}">
				<tr><td><input type="checkbox" name="${book.getName()}" value="${book.getName()}">${book.getName()}</input></td></tr>
				</c:if>	
				<c:if test="${HALO == true}">
				<tr><td><input type="checkbox" name="${book.getName()}" value="${book.getName()}" checked="checked">${book.getName()}</input></td></tr>
				</c:if>
				
		</c:forEach>
		
			
			<tr><td><input align="top" type="submit"  value="Save" /></td></tr>
         <input type="hidden" name="orderId" value="${order.getId()}" />
			</table>
        </form>
	

	
	
</body>
</html>