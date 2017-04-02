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
		<form action="${sessionScope.PROJECT_NAME}administratorSite/orders/read" method="GET"><input type="submit" value="Read" /></form><br>
		<form action="${sessionScope.PROJECT_NAME}administratorSite/orders/create" method="GET"><input type="submit" value="Create" /></form><br>
		<form action="${sessionScope.PROJECT_NAME}administratorSite/orders/update" method="GET"><input type="submit" value="Update" /></form><br>
		<form action="${sessionScope.PROJECT_NAME}administratorSite/orders/delete" method="GET"><input type="submit" value="Delete just order" /></form><br>
		</h2>
	</center>
</body>
</html>