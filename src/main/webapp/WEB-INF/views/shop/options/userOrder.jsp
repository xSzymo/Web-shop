<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user order</title>

</head>
<body>
<form id="Back123" action="${sessionScope.PROJECT_NAME}shop" method="get"></form>
		<form method="get" action="accept">
			<table border="0" width="25%">
				<tr>
				<c:forEach items="${payments}" var="payment">
					<td><input type="radio" name="payment" value="${payment}">${payment}</input></td>
				</c:forEach>
				</tr>			  	  
				 <tr>
                        <td>price</td>
                        <td><input type="hidden" name="price" value="${price}"/><c:out value="${price}" /></td>
                    	</tr>	  	  
				 <tr>
                        <td>e-mail</td>
                        <td><input type="text" name="email" value="${user.geteMail()}"/></td>
                    	</tr>
			<tr><td></td></tr>
			  	  <tr>
                       <td>billing address</td>
                    </tr>
				 <tr>
                        <td>street</td>
                        <td><input type="text" name="shippingAddressStreet" value="${user.getAddress().getStreet()}" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="shippingAddressPostalCode" value="${user.getAddress().getPostalCode()}" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="shippingAddressCity" value="${user.getAddress().getCity()}" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="shippingAddressCountry" value="${user.getAddress().getCountry()}" /></td>
                    </tr>
																	
				<tr><td></td></tr>
                    <tr>
                        <td>shipping address</td>
                    </tr>
				 <tr>
                        <td>street</td>
                        <td><input type="text" name="billingAddressStreet" value="${user.getAddress().getStreet()}" /></td>
                    </tr>
                    <tr>
                        <td>postalCode</td>
                        <td><input type="text" name="billingAddressPostalCode" value="${user.getAddress().getPostalCode()}" /></td>
                    </tr>
                    <tr>
                        <td>city</td>
                        <td><input type="text" name="billingAddressCity" value="${user.getAddress().getCity()}" /></td>
                    </tr>
                    <tr>
                        <td>country</td>
                        <td><input type="text" name="billingAddressCountry" value="${user.getAddress().getCountry()}" /></td>
                    </tr>
                    <tr>
                        <td>coupon code</td>
                        <td><input type="text" name="couponCode" value="" /></td>
                    </tr>
			<tr><td><input align="top" type="submit" value="accept" />
			<td><input type="submit" value="Back" form="Back123" /></td></tr>
				<input type="hidden" name="price" value="${price}"/>
			
				<br>
				<br>
			
			
<c:forEach items="${basket}" var="book">
			<table border="0" width="13%">
			--------------------------------------------<br>
			
			<tr><c:out value="Name : ${book.name}" /><br></tr>
			<c:set var="howMany" value="${0}"/>
				<c:forEach items="${basketWithAllBooks}" var="book1">
					<c:if test="${book1.getId() == book.getId()}"><c:set var="howMany" value="${howMany+1}"/></c:if>
				</c:forEach>
		
			<c:out value="books : ${howMany}" />
			</table>
			</c:forEach>
			
				
        <c:forEach items="${books}" var="book">
         <input type="hidden" name="books" value="${book}" />
		</c:forEach>
			</table>
		</form>



</body>
</html>
