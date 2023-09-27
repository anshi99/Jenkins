<%@page import="com.nagarro.training.constants.Constants"%>
<%@page import="com.nagarro.training.models.User"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>${title }</title>

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
					<h4 class="mt-3 flex-grow-1 text-center">
						<c:out value="${formHead}"></c:out>
					</h4>
				</div>

				<div class="px-4">
					<form action="" method="post" id="book_form">
						<table class="table table-borderless">
							<tbody>
								<tr>
									<td><label class="form-label" for="bid">Book Code</label></td>
									<td><input class="form-control" type="number" name="bid"
										id="bid"
										<c:if test="${book != null}">value="<c:out value="${ book.bid}"></c:out>" readonly</c:if> />
										<small id="bid_error" style="color: #f41f1f; display: none;">
									</small></td>
								</tr>
								<tr>
									<td><label class="form-label" for="bname">Book
											Name</label></td>
									<td><input class="form-control" type="text" name="bname"
										id="bname"
										<c:if test="${book != null}">value="<c:out value="${ book.bname}"></c:out>" </c:if> /><small
										id="bname_error" style="color: #f41f1f; display: none;">
									</small></td>
								</tr>
								<tr>
									<td><label class="form-label" for="author">Author</label></td>
									<td><select class="form-control" id="author">
											<c:if test="${authors != null}">
												<c:forEach var="author" items="${authors}">
													<option
														value='<c:out
															value="${author.aid}"></c:out>'
														<c:if test="${author.aid == book.author.aid }">selected</c:if>><c:out
															value="${author.aname}"></c:out>
													</option>
												</c:forEach>
											</c:if>
									</select></td>
								</tr>
								<tr>
									<td><label class="form-label" for="added_on">Added
											On</label></td>
									<td><input class="form-control" type="text" name="bdate"
										id="added_on"
										<c:choose>
											<c:when test="${book != null}">value="<c:out value="${book.addedOn}"></c:out>"</c:when>
											<c:otherwise>value="<c:out value="${addedOn}"></c:out>"</c:otherwise>
										</c:choose>
										readonly /></td>
								</tr>
								<tr>
									<td><button type="submit" class="btn btn-primary">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn btn-danger"
											onClick="window.location.replace('/LibraryManagementWeb/')">Cancel</button></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
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