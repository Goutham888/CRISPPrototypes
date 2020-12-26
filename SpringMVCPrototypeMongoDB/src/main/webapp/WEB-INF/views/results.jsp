<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>

<link href="https://unpkg.com/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet"/>

<body>
	<div class="container-fluid">
		<table border="1" class="table table-striped table-bordered">
			<tr class="thead-dark">
				<th>Store</th>
				<th>Item</th>
				<th>Quantity</th>
				<th>Address</th>
			</tr>
			<c:forEach items="${results}" var="store">
				<tr>
					<td> ${store.getStore()}</td>
					<td>${store.getItem()}</td>
					<td>${store.getQuantity()}</td>
					<td>${store.getAddress()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>