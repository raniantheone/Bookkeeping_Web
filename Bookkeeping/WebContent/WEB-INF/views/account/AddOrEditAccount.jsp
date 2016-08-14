<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="accountForEdit" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.accountForEdit }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/WEB-INF/views/SignOut.jsp" />

<form action="addOrEditAccount" method="post">
	<span>Account Name</span><input type="text" name="accountName" value="${ accountForEdit.accountName }" />
	<br>
	<span>Account Description</span><input type="text" name="accountDescription" value="${ accountForEdit.accountDesc }" />
	<br>
	<input type="hidden" name="accountIdToEdit" value="${ accountForEdit.accountId }" />
	<input type="submit" value="Submit" />
</form>

</body>
</html>