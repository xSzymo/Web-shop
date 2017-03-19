<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>Update</title>
</head>
<body>
<center>
			<c:if test="${book != null}">
				<c:set var="book" scope="page" value="${book}"/>
			</c:if>
						
        <form action="update">
            <table border="0" width="20%" cellpadding="3">
                    <tr>
                        <th colspan="2">Registration</th>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td><input type="hidden" name="id" value="<c:out value="${book.id}"/>"/>
						<c:out value="${book.id}"/></td>
                    </tr>
                    <tr>
                        <td>name</td>
                        <td><input type="text" name="name" value="<c:out value="${book.name}"/>" /></td>
                    </tr>
                        <td>author</td>
                        <td><input type="text" name="author" value="<c:out value="${book.author}"/>" /></td>
                    </tr>
					<tr>
                        <td>language</td>
                        <td><input type="text" name="language" value="<c:out value="${book.language}"/>" /></td>
                    </tr>
					<tr>
                        <td>price</td>
                        <td><input type="text" name="price" value="<c:out value="${book.price}"/>" /></td>
                    </tr>
					<tr>
                        <td>description</td>
                        <td><input type="text" name="description" value="<c:out value="${book.description}"/>" /></td>
                    </tr>

 					<c:forEach items="${categories}" var="category"  >
                      <tr>
					   	<td>  <input type="checkbox" name="<c:out value="${category.getName()}"/>" value="${category.getName()}"><c:out value="${category.getName()}"/></td>
					</tr>
					</c:forEach> 
                    <tr>
                        <td><input type="submit" value="Update" /></td>
                    </tr>
            </table>
        </form>



		<form action="/CRUD/administratorSite/books/update">
    		<input type="submit" value="Back" />
		</form>

	<c:if test="${msg != null}">
		<c:out value="${msg}"></c:out>
	</c:if>
	
	
	
	
	
	
	
	


            <table border="0" width="20%" cellpadding="3">
<tr><td>

<br><br>
	<form method="POST" action="uploadFilePictureWithId" enctype="multipart/form-data">
        <input type="hidden" name="bookId" value="${book.id}"/>
		File to upload: <input type="file" name="file"><br /><br>
		 Name:<br> <input type="text" name="name"><br /><br>
		 <input type="submit" value="add"> 
		 <c:if test="${msg != mull}">
		 <c:out value="${msg}"></c:out>
		 </c:if>
	</form>
	</td>
	<td>
<br><br>
	<form action="uploadFileLinkId">
        <input type="hidden" name="bookId" value="${book.id}"/>
		Link: <input type="text" name="link"><br /> <br /> 
		Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="add"> 
		 <c:if test="${msgLink != mull}">
			 <c:out value="${msgLink}"></c:out>
		 </c:if>
	</form>
	
	</table>
	<td></td><tr>
	</tr></table>
	</center>
	
	
	
				 <form action="deletePicture/${book.id}">
     			 <input type="hidden" name="bookId" value=""/>
       			 <input type="hidden" name="picture" value=""/>
				</form>
	
	
	
		<center>		
		<br><br><br><br>	
		
            <table border="0" width="20%" cellpadding="3">
				<c:forEach items="${book.pictures}" var="picture">
				 <form action="deletePicture">
     			 <input type="hidden" name="bookId" value="${book.id}"/>
       			 <input type="hidden" name="pictureId" value="${picture.id}"/>
				
				<td>
						&nbsp;<input align="top" type="submit" value="Delete" /><br>
							<IMG HEIGHT="100" WIDTH="100" SRC="/CRUD/getImage/${picture.name}">
				</td>
  			 </form>
			</c:forEach>
			</table>
	</center>
	
	
</body>
</html>