<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Create</title>
</head>
<!-- how to make order with books (something about add books to order  -->
	<center>


		<h2>Create order :</h2>

		<form method="get" action="createOrder">
			<table border="0" width="25%">
				<tr>
				<c:forEach items="${payments}" var="payment">
					<td><input type="radio" name="payment" value="${payment}">${payment}</input></td>
				</c:forEach>
				</tr>			  	  
				 <tr>
                        <td>price</td>
                        <td><input type="text" name="price" /></td>
                    </tr>
			<tr><td></td></tr>
			  	  <tr>
                       <td>billing address</td>
                    </tr>
				 <tr>
                        <td>street</td>
                        <td><input type="text" name="shippingAddressStreet" value="${shippingAddress.street}" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="shippingAddressPostalCode" value="${shippingAddress.postalCode}" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="shippingAddressCity" value="${shippingAddress.city}" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="shippingAddressCountry" value="${shippingAddress.country}" /></td>
                    </tr>

				<tr><td></td></tr>
                    <tr>
                        <td>shipping address</td>
                    </tr>
				 <tr>
                        <td>street</td>
                        <td><input type="text" name="billingAddressStreet" value="${billingAddress.street}" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="billingAddressPostalCode" value="${billingAddress.postalCode}" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="billingAddressCity" value="${billingAddress.city}" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="billingAddressCountry" value="${billingAddress.country}" /></td>
                    </tr>
			<tr><td></td></tr>
                    <tr>
                        <td>CouponCode</td>
                    </tr>

                    <tr>
                        <td>code</td>
                        <td><input type="text" name="couponCode" value="${couponCode.code}" /></td>
                    </tr>
                    <tr>
                        <td>code discount</td>
                        <td><input type="text" name="couponCodeDiscount" value="${couponCode.getCodeDiscount()}" /></td>
                    </tr>
				<br>
				<br>
				<tr><td>
				
			<tr><td></td></tr>
                    <tr>
                        <td>Books</td>
					<c:if test="${books == null}">
					<td>choose books</c:if>
                    </tr>
			 <c:forEach items="${books}" var="book">
					<tr><td>${book}</td></tr>
				</c:forEach>
			
			<tr><td></td></tr>
				<tr>
					<td>Relized
					<td><input type="radio" name="realized" value="${true}">yes	</input></td>
					<td><input type="radio" name="realized" value="${false}">no	</input></td>
					</td>
				</tr>
				<tr>
				<td><input type="submit" value="send" />
				</td></tr>
        <input type="hidden" name="billingAddress" value="${billingAddress.getId()}" />
         <input type="hidden" name="shippingAddress" value="${shippingAddress.getId()}" />
         <input type="hidden" name="couponCodeId" value="${couponCode.getId()}" />
        <c:forEach items="${books}" var="book">
         <input type="hidden" name="books" value="${book}" />
		</c:forEach>
			</table>
		</form>
		
		
		
		
		<br><br><br>
		<!--CouponCode  CouponCode  CouponCode  CouponCode  CouponCode  CouponCode  CouponCode  CouponCode  -->
		<h3>Coupon codes</h3>
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
        <input type="hidden" name="billingAddress" value="${billingAddress.getId()}" />
         <input type="hidden" name="shippingAddress" value="${shippingAddress.getId()}" />
         <input type="hidden" name="couponCodeId" value="${couponCode.getId()}" />

	 <c:forEach items="${books}" var="book">
         <input type="hidden" name="books" value="${book}" />
	</c:forEach>

			<input align="top" type="submit"  value="Save" />
        </form>

		
		
		
		
		
		
		
		<br><br>
		<!--ADDRESS   ADDRESS   ADDRESS   ADDRESS   ADDRESS   ADDRESS   ADDRESS   ADDRESS   -->
		<h3>Address</h3>
 <form method="get" action="createAddress">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <td>street</td>
                        <td><input type="text" name="street" value="" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="postalCode" value="" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="city" value="" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="country" value="" /></td>
                    </tr>

				<tr><td><input type="radio" name="address" value="shipping">shipping address</input></td>
				<td><input type="radio" name="address" value="billing">billing address</input></td></tr>
				
            </table>
			
			<br><input align="top" type="submit"  value="Save" /><br><br>
			
        <input type="hidden" name="billingAddress" value="${billingAddress.getId()}" />
         <input type="hidden" name="shippingAddress" value="${shippingAddress.getId()}" />
         <input type="hidden" name="couponCodeId" value="${couponCode.getId()}" />
         	 <c:forEach items="${books}" var="book">
         <input type="hidden" name="books" value="${book}" />
		</c:forEach>
        </form>


<br><br>
<!-- BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS BOOKS  -->

<h3>Books</h3>
 <form method="get" action="createBook">
            <table border="0" width="20%" cellpadding="3">
                <c:forEach items="${allBooks}" var="book">
					<tr><td><input type="checkbox" name="${book.getName()}" value="${book.getName()}">${book.getName()}</input></td></tr>
				</c:forEach>
			
			<tr><td><input align="top" type="submit"  value="Save" /></td></tr>
      	  <input type="hidden" name="billingAddress" value="${billingAddress.getId()}" />
         <input type="hidden" name="shippingAddress" value="${shippingAddress.getId()}" />
         <input type="hidden" name="couponCodeId" value="${couponCode.getId()}" />
			</table>
        </form>
				
</body>
</html>