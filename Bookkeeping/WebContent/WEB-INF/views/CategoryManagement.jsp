<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="categories" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.categories }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/WEB-INF/views/SignOut.jsp" />

<!-- trigger add category form -->
<form action="serveAddOrEditForm" method="post">
	<input type="submit" value="add a category" />
</form>

<!-- display all categories of current user -->
<table>
	<tr>
		<th>Category Name</th><th>Edit</th><th>Delete</th>
	</tr>
	<c:forEach var="category" items="${ categories }">
		<tr>
			<td>${ category.categoryName }</td>
			<td>
				<form action="serveAddOrEditForm" method="post">
					<input type="hidden" name="categoryIdForEdit" value="${ category.categoryId }" />
					<input type="submit" value="Edit" />
				</form>
			</td>
			<td>
				<form action="deleteCategory" method="post">
					<input type="hidden" name="categoryIdForDel" value="${ category.categoryId }" />
					<input type="submit" value="Delete" />
				</form>
			</td>
		</tr>			
	</c:forEach>
</table>

</body>
</html>