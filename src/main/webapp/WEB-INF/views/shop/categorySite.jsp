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

<div align="right">
<a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}shop/basket"><h1>Basket</h1></a>
</div>

	<center>
			
			<c:forEach items="${books}" var="book">
			<table border="0" width="13%">
			--------------------------------------------<br>
			
			<tr><c:out value="Name : ${book.name}" /><br></tr>
			
			<tr><c:out value="Author :${book.author}" /><br></tr>
			
			<tr><c:out value="Language :${book.language}" /><br></tr>
			
			<tr><c:out value="Price :${book.price}" /><br></tr>
			
			<tr><c:out value="Description :${book.description}" /><br><br><br></tr>
			
			<c:forEach items="${book.pictures}" var="picture">
			<IMG HEIGHT="100" WIDTH="100" SRC="${sessionScope.PROJECT_NAME}getImage/${picture.name}">
			</c:forEach>
			<form action="categorySite/${book.getId()}"><tr><td>
			<input align="top" type="submit" value="add" />
			<input type="number" value="add" id="number" name="number" value="0" min="0"/>
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
