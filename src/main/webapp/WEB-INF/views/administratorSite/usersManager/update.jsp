<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/users" method="get"></form>
		

			<c:if test="${user != null}">
				<c:set var="user" scope="page" value="${user}"/>
			</c:if>
						

        <form action="update/updateOne" method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></input>
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Update</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="text" name="id" value="<c:out value="${user.id}"/>"/></td>
                    </tr>
                    <tr>
                        <td>login</td>
                        <td><input type="text" name="login" value="<c:out value="${user.login}"/>" /></td>
                    </tr>
                        <td>password</td>
                        <td><input type="text" name="password" value="<c:out value="${user.password}"/>" /></td>
                    </tr>
					<tr>
                        <td>name</td>
                        <td><input type="text" name="name" value="<c:out value="${user.name}"/>" /></td>
                    </tr>
					<tr>
                        <td>surname</td>
                        <td><input type="text" name="surname" value="<c:out value="${user.surname}"/>" /></td>
                    </tr>
					<tr>
                        <td>eMail</td>
                        <td><input type="text" name="eMail" value="<c:out value="${user.eMail}"/>" /></td>
                    </tr>
					<tr>
                        <td>date</td>
                        <td><input type="date" name="date" value="" /></td>
                    </tr>
<!-- 					                    <tr>
                        <td>  <input type="checkbox" name="Admin" value="ROLE_ADMIN">admin<br></td>
                        <td>  <input type="checkbox" name="User" value="ROLE_USER" checked="checked">user<br></td>
                    </tr> -->
                    <tr>				
                        <td><input type="submit" value="Update" /></td>
                    <td>
		<input type="submit" value="Back" form="Back" />
		</td>
            </table>
        </form>
				






	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>

	<center>
			<c:forEach items="${users}" var="user">
			<table border="0" width="13%">
			--------------------------------------------<br>
			<tr>Book id : <c:out value="${user.id}"></c:out><br></tr>
			
			<tr><c:out value="login : ${user.login}" /><br></tr>
			
			<tr><c:out value="password :${user.password}" /><br></tr>
			
			<tr><c:out value="name :${user.name}" /><br></tr>
			
			<tr><c:out value="surname :${user.surname}" /><br></tr>
			
			<tr><c:out value="eMail :${user.eMail}" /><br></tr>
			
			 <c:forEach items="${address}" var="address">
					<c:if test="${address.getId() == user.getAddress().getId()}">
						<tr>Country : <c:out value="${address.getCountry()}" /></tr><br>
					</c:if>
			</c:forEach> 
			
			<c:forEach items="${roles}" var="role">
				<c:forEach items="${role.getUser()}" var="userRole">
					<c:if test="${userRole.getId() == user.getId()}">
						<tr>Role : <c:out value="${role.getRole()}" /><br></tr>
					</c:if>
				</c:forEach>
				</c:forEach>
							
			 <form action="update/${user.id}">
				<br><input align="top" type="submit" value="Update" />
  			 </form>
			</table>
			</c:forEach>
	</center>
</body>
</html>