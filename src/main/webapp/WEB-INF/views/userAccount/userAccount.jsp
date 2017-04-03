<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>account</title>
</head>
<body>
            <table border="0" width="14%" cellpadding="1">
                    <tr>
                        <td>Orders</td>
                  		      <td>        <form method="get" action="account/orders">
								<input align="top" type="submit"  value="Click" />
     				   </form>
					</td>
                    </tr>
                    <tr>
                        <td>Informations</td>
                      	  <td>        <form method="get" action="account/informations">
								<input align="top" type="submit"  value="Click" />
      				 	 </form>
					</td>
                    </tr>
                    <tr>
                        <td>Change e-mail</td>
                        <td>        <form method="get" action="account/changeEmail">
							<input align="top" type="submit"  value="Click" />
       				 </form>
					</td>
                    </tr>
                    <tr>
                        <td>Change password</td>
                  	      <td>        <form method="get" action="account/changePasswd"> 
							<input align="top" type="submit"  value="Click" />
        				</form></td>
                    </tr>
                    <tr>
                        <td>Update data</td>
                  	      <td>        <form method="get" action="account/changeData">
							<input align="top" type="submit"  value="Click" />
        				</form></td>
                    </tr>
                    <tr>
                        <td>Shop</td>
                  	      <td>       <form id="Back" action="${sessionScope.PROJECT_NAME}shop" method="get"></form>
								<input type="submit" value="Back" form="Back" />
        				</form></td>
                    </tr>
					<c:if test="${isAdmin}">
                    <tr>
                        <td>Administrator site</td>
                  	      <td>       
						 		<form id="administratorSite" action="${sessionScope.PROJECT_NAME}administratorSite" method="get"></form>
								<input type="submit" value="Go" form="administratorSite" />
						</td>
                    </tr>
					</c:if>
       		 </table>
</body>
</html>
