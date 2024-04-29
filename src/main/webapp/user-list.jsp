<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management Application</title>
</head>
<body>
	<header>
		<nav class="navbar">
			<div>
				<a href="http://www.javaguides.net" class="navbar-heading">User
					Management App</a>
			</div>
			<ul>
				<li><a href="<%=request.getContextPath()%>/list">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="row">
		<div class="container">
			<h3>List of Users</h3>
			<hr>
			<div>
				<a href="<%=request.getContextPath()%>/new">Add New User</a>
			</div>
			<br>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Country</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user"  items="${listUser}">
						<tr>
							<td><c:out value="${user.id}"></c:out></td>
							<td><c:out value="${user.name}"></c:out></td>
							<td><c:out value="${user.email}"></c:out></td>
							<td><c:out value="${user.country}"></c:out></td>
							<td><a href="edit?id=<c:out value="${user.id}"/>">Edit</a>
								<a href="delete?id=<c:out value="${user.id}" />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>