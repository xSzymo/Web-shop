<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>

<!-- Fix : sometimes give diffrent url like books/books/create or books/create(correct) -->

		<form id="saveForm" action="books" method="get"></form>
		<form id="deleteForm" action="registration" method="get"></form>


		<input type="submit" value="Books" form="saveForm" />&nbsp;
		<input type="submit" value="Users" form="deleteForm" /><br>
		
		</center>
</body>
</html>