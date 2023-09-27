<%@page import="com.nagarro.training.constants.Constants"%>
<%@page import="com.nagarro.training.models.User"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Books Listing</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<!-- Custom CSS -->
<link
	href="<c:url value="${PageContext.request.contextPath}/resources/css/styles.css" />"
	rel="stylesheet">

</head>

<body>
	<div class="container-fluid">
		<%
		User user = (User) session.getAttribute(Constants.USER_SESSION_ATTRIBUTE);
		if (user == null) {
			response.sendRedirect("/LibraryManagementWeb/login");
		}
		%>
		<div class="container">
			<div class="d-flex py-3 align-items-end flex-column">
				<p class="mt-2 px-3">
					Welcome
					<%
				if (user != null) {
					out.println(user.getUsername());
				}
				%>
					<button type="button" class="btn btn-outline-primary btn-sm mt-2"
						onclick="window.location.replace('./logout');">Logout</button>
			</div>

			<div class="border border-dark">
				<div class="ms-4 d-flex px-3 ">
					<h4 class="mt-3 flex-grow-1 text-center">Books Listing</h4>
					<p class="mt-2 mx-3">
						<button type="button" class="btn btn-primary btn-sm mt-2"
							onclick="window.location.replace('./books/book');">Add a
							Book</button>
				</div>

				<c:if test="${books != null}">
					<div class="px-4">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Book Code</th>
									<th>Book Name</th>
									<th>Author</th>
									<th>Data Added</th>
									<th>Action</th>
								</tr>
							</thead>

							<c:forEach var="book" items="${books}">
								<tbody>
									<tr>
										<td><c:out value="${book.bid}"></c:out></td>
										<td><c:out value="${book.bname}"></c:out></td>
										<td><c:out value="${book.author.aname}"></c:out></td>
										<td><c:out value="${book.addedOn}"></c:out></td>
										<td><button type="button" class="btn btn-outline-primary"
												onClick="window.location.replace('books/book?bid=' + '<c:out value="${book.bid}"></c:out>')">Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;
											<button type="button" class="btn btn-outline-danger"
												onClick="ajaxDeleteBookRequest(<c:out value="${book.bid}"></c:out>)">Delete</button></td>
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<!-- BootStrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<!-- JQUERY -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<!-- Custom JS -->
	<script
		src="<c:url value="${PageContext.request.contextPath}/resources/js/scripts.js" />"></script>
</body>