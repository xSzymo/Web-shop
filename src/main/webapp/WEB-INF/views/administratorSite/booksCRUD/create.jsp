<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Create</title>
</head>
<body>
		<form id="Back" action="${sessionScope.PROJECT_NAME}administratorSite/books" method="get"></form>
		<input type="submit" value="Back" form="Back" />

<center>
<h2>Create book :</h2>
		        <form method="get" action="createBook">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="" /></td>
                    </tr>
                    <tr>
                        <td>Autohor</td>
                        <td><input type="text" name="author" value="" /></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="description" value="" /></td>
                    </tr>
                    <tr>
                        <td>Language</td>
                        <td><input type="text" name="langauge" value="" /></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="price" value="" /></td>
                    </tr>
                    <tr>
                        <td>Picture</td>
                        <td><input type="hidden" name="pictureName" value="<c:out value="${pictureName}"/><c:out value="${pictureLinkName}"/>"/>
						<c:out value="${pictureName}"/><c:out value="${pictureLinkName}"/> </td>
					</tr>
 					<c:forEach items="${categories}" var="category"  >
                      <tr>
					   	<td>  <input type="checkbox" name="<c:out value="${category.getName()}"/>" value="${category.getName()}"><c:out value="${category.getName()}"/></td>
					</tr>
					</c:forEach> 
            </table>
			
				<c:if test="${msgError != null}">
					<c:out value="${msgError}"></c:out><br>
					<c:set var="pictureName" scope="page" value="hey"/>
				</c:if>
				
				<c:if test="${msgSuccess != null}">
					<c:out value="${msgSuccess}"></c:out><br>
					
						<c:if test="${pictureName != null}">
							<c:set var="pictureName" scope="page" value="${pictureName}"/>
						</c:if>
					
						<c:if test="${pictureLinkName != null}">
							<c:set var="pictureLinkName" scope="page" value="${pictureLinkName}"/>
						</c:if>
				</c:if>
			
			<br><input align="top" type="submit"  value="Save" /><br><br>
        </form>
         <table border="0" width="20%" cellpadding="3">
<tr><td>









<br><br>
	<form method="POST" action="uploadFilePicture?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /><br>
		 Name:<br> <input type="text" name="name"><br /><br>
		 <input type="submit" value="upload"> 
		 <c:if test="${msg != mull}">
		 <c:out value="${msg}"></c:out>
		 </c:if>
	</form>
	
	</td>
	<td>
<br><br>
	<form action="uploadFileLink">
		Link: <input type="text" name="link"><br /> <br /> 
		Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="upload"> 
		 <c:if test="${msgLink != mull}">
			 <c:out value="${msgLink}"></c:out>
		 </c:if>
	</form>
	
	</table>
	<td></td><tr>
	
	
	
<!-- <img src="getImage/123" alt="car_image"/> -->
	
	
	
</body>
</html>