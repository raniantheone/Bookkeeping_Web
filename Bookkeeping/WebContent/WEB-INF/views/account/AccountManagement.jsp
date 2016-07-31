<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="accounts" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.accounts }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="serveAddOrEditAccountForm" method="post">
	<input type="submit" value="Add an account" />
</form>

<table>
	<tr>
		<th>Account Name</th>
		<th>Account Description</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="account" items="${ accounts }">
		<tr>
			<td>${ account.accountName }</td>
			<td>${ account.accountDesc }</td>
			<td>
				<form action="serveAddOrEditAccountForm" method="post">
					<input type="hidden" name="accountIdForEdit" value="${ account.accountId }" />
					<input type="submit" value="Edit" />
				</form>
			</td>
			<td>
				<form action="deleteAccount" method="post">
					<input type="hidden" name="accountIdForEdit" value="${ account.accountId }" />
					<input type="submit" value="DELETE" />
				</form>
			</td>
		</tr>	
	</c:forEach>
</table>

</body>
</html>