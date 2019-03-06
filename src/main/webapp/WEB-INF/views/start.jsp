<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>xSzymo</title>
</head>
<center>

<h1><a href="shop">Go to start page</a></h1>

<br><br><br><br><br><br><br><br><br>
	<h1>Hey, welcome on start page</h1>
	<h2>
		This is my little web shop project, currently in progress. Enjoy clicking :)<br><br>
		Actual modules : <h3>
			- administrator site here (you have to login on admin acc before (try admin, admin) : <a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}administratorSite">click</a><br>
			- login/logout with click to remember(reset) password or e-mail here : <a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}login">click</a><br>
			- user site where you can change your data or see orders/account informations (you have to login on acc before) here : <a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}login">click</a><br>
			- shop with shopping basket here : <a href="${sessionScope.URL}${sessionScope.PROJECT_NAME}shop">click</a><br>
		</h3>
		<h4>
		<br><a href="http://www.xSzymo.com">My site</a>
		<br><a href="https://github.com/xSzymo">Github</a>
		<br><a href="https://www.youtube.com/user/xszymo">YT</a>
		</h4>
	</h2>	
</center>
</body>
</html>