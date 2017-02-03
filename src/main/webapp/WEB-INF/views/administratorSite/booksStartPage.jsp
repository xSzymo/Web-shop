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
		<h2>Books CRUD</h2>
		<h2>
		<form action="books/create" method="GET"><input type="submit" value="Create" /></form><br>
		<form action="books/read" method="GET"><input type="submit" value="Read" /></form><br>
		<form action="books/update" method="GET"><input type="submit" value="Update" /></form><br>
		<form action="books/delete" method="GET"><input type="submit" value="Delete" /></form><br>
		</h2>
	</center>

</body>
</html>