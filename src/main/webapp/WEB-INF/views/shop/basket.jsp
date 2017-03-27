<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>



	<center>
			<c:out value="Price to pay : ${price} gold" /><br>
			<form action="continue">
			<input align="top" type="submit" value="continue" />
			</form>
			
			<br><br>
			
			
			<c:forEach items="${basket}" var="book">
			<table border="0" width="13%">
			--------------------------------------------<br>
			
			<tr><c:out value="Name : ${book.name}" /><br></tr>
			
			<tr><c:out value="Price :${book.price}" /><br></tr><br>
			
			<c:set var="howMany" value="${0}"/>
				<c:forEach items="${basketWithAllBooks}" var="book1">
					<c:if test="${book1.getId() == book.getId()}"><c:set var="howMany" value="${howMany+1}"/></c:if>
				</c:forEach>
		
			 <c:out value="All price : ${(book.price * howMany)}" /><br>
			<c:out value="All books : ${howMany}" />
			
			<form action="delete"><tr><td>
			<input align="top" type="submit" value="delete" />
			<input type="number" value="add" id="number" name="number" value="0" min="0"/>
			<input type="hidden"  id="id" name="id" value="${book.getId()}" />
			</td></tr></form>
			</table>
			</c:forEach>
		</center>
		
<%-- 
	<table border="1" width="15%">			
		<c:forEach items="${books}" var="book">
		
				 <form action="shop/categorySite/${category.getName()}">
					<tr><td><input align="top" type="submit" value="${category.getName()}" /></td></tr>
			 </form>
		</c:forEach>
	</table>
     --%>
</body>
</html>
