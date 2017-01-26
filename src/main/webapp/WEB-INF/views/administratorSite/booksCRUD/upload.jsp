<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>

<%@page import="java.math.BigInteger"%>
<%@page import="javax.xml.bind.DatatypeConverter"%>
<%@page import="java.awt.image.BufferedImage"%>

<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>

	<form method="POST" action="uploadFile" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> Name: <input
			type="text" name="name"><br /> <br /> <input type="submit"
			value="upload"> Press here to upload the file!
	</form>


	<form action="uploadFil" enctype="multipart/form-data">
		Name: <input type="text" name="name"><br /> <br /> <input
			type="submit" value="upload"> Press here to upload the file!
	</form>

	<img src="E:/test111/45" />
	<img src='<c:url value="E:/test111/45"/>' class="45" />
	<img scr="FileServet?path=E:/test111/45"> 
<img src="https://i.stack.imgur.com/GsDIl.jpg" width="100" height="100">

 <img src="/CRUD/getImage/45" alt="myImage"/>

	
	
	
</body>
</html>