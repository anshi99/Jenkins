<%@page import="com.nagarro.training.constants.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<!-- Custom CSS -->
<link
	href="<c:url value="${PageContext.request.contextPath}/resources/css/styles.css" />"
	rel="stylesheet">

</head>

<body class="parent">
	<%
	if (session.getAttribute(Constants.USER_SESSION_ATTRIBUTE) != null) {
	%>
	<c:redirect url="/" />
	<%
	}
	%>
	<div class="main-div child">
		<div class="header-div">
			<h3 class="header-text">Login</h3>
		</div>
		<form action="login" method="post" id="login_form">
			<div class="form-div">
				<table>
					<tr>
						<td class="td-label"><label for="username">Username:</label></td>
						<td class="td-input"><span style="color: #f41f1f;">*</span> <input
							type="text" name="username" id="username" /> <small
							id="username_error" style="color: #f41f1f; margin-left: 1em;">
						</small></td>
					</tr>
					<tr>
						<td class="td-label"><label for="password">Password:</label></td>
						<td class="td-input"><span style="color: #f41f1f;">*</span> <input
							type="password" name="password" id="password" /> <small
							id="password_error" style="color: #f41f1f; margin-left: 1em;">
						</small></td>

					</tr>
					
				</table>
			</div>
			<div class="button-div">
				<button type="submit">
					<small>Login >></small>
				</button>
			</div>
		</form>

		<div>
			<p id="login_loading" style="display: none">Loading...</p>
		</div>
	</div>

	<!-- JQUERY -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<!-- Custom JS -->
	<script
		src="<c:url value="${PageContext.request.contextPath}/resources/js/scripts.js" />"></script>

</body>

</html>