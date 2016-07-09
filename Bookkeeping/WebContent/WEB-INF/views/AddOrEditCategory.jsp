<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="categoryToEdit" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.categoryToEdit }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="addOrEditCategory" method="post">
	<input type="text" name="categoryName" value="${ categoryToEdit.categoryName }" />
	<input type="hidden" name="categoryIdForEdit" value="${ categoryToEdit.categoryId }" />
	<input type="submit" value="confirm" />
</form>

</body>
</html>