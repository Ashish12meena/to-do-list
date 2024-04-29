<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user form</title>
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
	<div>
		<div>
			<div class="card-body">
				<c:if test="${user !=null }">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user ==null }">
					<form action="insert" method="post">
				</c:if>
				<div class="caption">
					<h2>
						<c:if test="${user !=null }">
							Edit User
						</c:if>
						<c:if test="${user ==null }">
							Add New User
						</c:if>
					</h2>
				</div>
				<c:if test="${user !=null }">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out value='${user.name}'></c:out>" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value="<c:out value='${user.email}'></c:out>" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>User Country</label> <input type="text"
						value="<c:out value='${user.country}'></c:out>"class="form-control"
						name="country">
				</fieldset>
				<button type="submit" class="btn">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>