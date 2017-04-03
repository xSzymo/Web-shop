<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Read</title>
</head>
<body>

  <form method="get" action="readOne">
  Name :  <input type="text" name="login" value="" />
		<input align="top" type="submit" value="Find" />
   </form>
 <form method="get" action="read">
		<input align="top" type="submit" value="Read more" />
   </form>

		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/users" method="get"></form>
		<input type="submit" value="Back" form="Back" />

<c:if test="${user != null}"> 
			<table border="0" width="13%">
			<tr><c:out value="User id : ${user.id}"></c:out><br></tr>
			
			<tr><c:out value="Login : ${user.login}" /><br></tr>
			
			<tr><c:out value="Password : ${user.password}" /><br></tr>
			
			<tr><c:out value="Name : ${user.name}" /><br></tr>
			
			<tr><c:out value="Surname : ${user.surname}" /><br></tr>
			
			<tr><c:out value="E-mail : ${user.eMail}" /><br></tr>
			
			<tr><c:out value="Date of birth : ${user.dateBirth}" /><br></tr>
			
			<tr><c:out value="Country : ${address.getCountry()}" /><br></tr>
						
			 			<c:forEach items="${roles}" var="role">
				<c:forEach items="${role.getUser()}" var="userRole">
					<c:if test="${userRole.getId() == user.getId()}">
						<tr>Role : <c:out value="${role.getRole()}" /></tr>
					</c:if>
				</c:forEach>
			</c:forEach> 
			
		</table>
</c:if> 





	<center>
			<c:forEach items="${users}" var="user">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr><c:out value="User id : ${user.id}"></c:out><br></tr>
			
			<tr><c:out value="Login : ${user.login}" /><br></tr>
			
			<tr><c:out value="Password : ${user.password}" /><br></tr>
			
			<tr><c:out value="Name : ${user.name}" /><br></tr>
			
			<tr><c:out value="Surname : ${user.surname}" /><br></tr>
			
			<tr><c:out value="E-mail : ${user.eMail}" /><br></tr>
			
			<tr><c:out value="Date of birth : ${user.dateBirth}" /><br></tr>
			
			 <c:forEach items="${address}" var="address">
					<c:if test="${address.getId() == user.getAddress().getId()}">
						<tr>Country : <c:out value="${address.getCountry()}" /></tr><br>
					</c:if>
			</c:forEach> 
						
 			<c:forEach items="${roles}" var="role">
				<c:forEach items="${role.getUser()}" var="userRole">
					<c:if test="${userRole.getId() == user.getId()}">
						<tr>Role : <c:out value="${role.getRole()}" /></tr>
					</c:if>
				</c:forEach>
			</c:forEach> 
			
			</table>
			</c:forEach>
		</center>
		
</body>
</html>