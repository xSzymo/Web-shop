<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<c:choose>
	<c:when test="${user == null}">
		<form id="saveForm" action="userLogin" method="get"></form>
		<form id="deleteForm" action="registration" method="get"></form>

		<input type="text" name="login" form="saveForm" value="login" /> <br>
		<input type="password" name="password" id="foo2" form="saveForm" value="password" />

		<input type="submit" name="save" value="Login" form="saveForm" />&nbsp;
		<input type="submit" name="delete" value="Register" form="deleteForm" /><br>
		<a href="login">Go to login page</a>
	</c:when>
	
	<c:when test="${user != null}">
		<!-- if user is present -->
		|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||<br>
		|||||||||||||||||||||||hello maj friend||||||||||||||||||||<br>
		||||||||||||||||||||||||||||||||||${user.getLogin()}||||||||||||||||||||||||||||
	</c:when>
</c:choose>


























<!-- 

<form id="saveForm" action="/post/dispatch/save" method="post"></form>

<div id="toolbar">
		<input type="submit" name="save" value="Save" form="saveForm"	onclick="alert(document.getElementById('deleteForm').elements.length + ' ' + document.getElementById('saveForm').elements.length + ' ' + document.getElementById('saveForm').elements['foo2'].value);return false;" />

</div>
 -->





<%--         <% 
            //if(request.getParameter("buttonName") != null) {
            if(request.getParameterNames() != null) {
        %>
            You clicked 
            <%= request.getParameter("buttonName") %>
        <%
            }
        %>

        <FORM NAME="form1" METHOD="POST">
            <INPUT TYPE="HIDDEN" NAME="buttonName">
            <INPUT TYPE="BUTTON" VALUE="Button 1" ONCLICK="button1()">
            <INPUT TYPE="BUTTON" VALUE="Button 2" ONCLICK="button2()">
        </FORM>

        <SCRIPT LANGUAGE="JavaScript">
            
            function button1()
            {
                document.form1.buttonName.value = "button 1"
                form1.submit()
            }    
            function button2()
            {
                document.form1.buttonName.value = "button 2"
                form1.submit()
            }    
            
        </SCRIPT> --%>



<!-- <form action="loginUser" method="GET" style="display: inline;"><td>
	<p style="text-align: right;">
	Username: &nbsp; <input type="text" name="first_name"> </td><td><br />
	Password : &nbsp; <input type="text" name="last_name" /> <br><br>
	 <input type="submit" value="Login" />
	 
</form>
<p style="text-align: right;">

<input type="button" value="Register" /></p>


		</p>

	<p style="text-align: left;">

		<input type="button" value="Login" /> <input type="button"
			value="Register" />
	</p>
</td>
 -->




</body>
</html>