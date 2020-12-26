<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<title>Custom login</title>
    </head>
	
	<link href="https://unpkg.com/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
	
    <body class="container-fluid">
        <h1>Custom login form</h1>
        
        <!-- invalid credentials error msg -->
        <c:if test="${not empty error}">
			<div>${error}</div>
		</c:if>
		
		<!-- logged out msg -->
		<c:if test="${not empty msg}">
			<div>${msg}</div>
		</c:if>
		
		<!-- custom login form -->
        <form name="loginform" action="<c:url value='/login'/>" method="POST">
            <table>
                <tr>
                    <td>Enter username:</td>
                    <td><input type='text' name='username' value=''></td>
                </tr>
                <tr>
                    <td>Enter password:</td>
                    <td><input type='password' name='password' /></td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan='2'><input name="submit" type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>