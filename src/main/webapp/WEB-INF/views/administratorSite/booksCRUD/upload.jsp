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
<right>
		<form id="Back" action="${sessionScope.PROJECT_NAME}books" method="get"></form>
		<input type="submit" value="Back" form="Back" />
</right>


	<form method="POST" action="uploadFile" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> Name: <input
			type="text" name="name"><br /> <br /> <input type="submit"
			value="upload"> Press here to upload the file!
	</form>


	<form action="uploadFil" enctype="multipart/form-data" methos="post">
		Name: <input type="text" name="name"><br /> <br /> <input
			type="submit" value="upload"> Press here to upload the file!
	</form>	
</body>
</html>