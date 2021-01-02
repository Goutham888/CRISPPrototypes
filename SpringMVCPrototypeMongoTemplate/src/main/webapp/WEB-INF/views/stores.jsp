<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <title>Secure page</title>    
	</head>
	
	<link href="https://unpkg.com/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet"/>   
	
	<body>
		<!-- store request form -->
        <form class="form-inline" name="productQuery" action="<c:url value='/productQuery'/>" method="POST">
             <p class="mb-2 mr-sm-2">I am looking for </p>
             <input class="form-control mb-2 mr-sm-2" list="products" name="product">
			  <datalist id="products">
			    <option value="water">
			    <option value="canned food">
			    <option value="soap">
			    <option value="toilet paper">
			  </datalist>
			  <p class="mb-2 mr-sm-2"> at </p> 
			  <input class="form-control mb-2 mr-sm-2" list="stores" name="store">
			  <datalist id="stores">
			    <option value="Harris Teeter">
			    <option value="Costco">
			    <option value="Walmart">
			  </datalist>
			  <p>.</p>
			  
  			<input type="submit" value="Search">
        </form>
	</body>
</html>