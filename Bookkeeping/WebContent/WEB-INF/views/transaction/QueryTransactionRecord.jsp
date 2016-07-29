<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="transTypeOpts" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.transactionTypes }" />
<c:set var="transactionRecords" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.pagedData.pagedDataList }" />
<c:set var="currentPage" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.pagedData.currentPage }" />
<c:set var="totalPages" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.pagedData.totalPages }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- Add transaction record: income, expense, or account transfer. -->
<form action="setupAddOrEditForm" method="post">

	<select name="transType">
		<c:forEach var="transOpt" items="${ transTypeOpts }" >
			<option value="${ transOpt.key }">${ transOpt.value }</option>
		</c:forEach>
	</select>
	
	<input type="submit" value="Add Transaction Record" />
	
</form>

<table>
	<tr>
		<th>Record Time</th>
		<th>Type</th>
		<th>Amount</th>
		<th>Category</th>
		<th>From Account</th>
		<th>To Account</th>
		<th>Note</th>
	</tr>
	<c:forEach var="transactionRecord" items="${ transactionRecords }">
		<tr>
			<td>${ transactionRecord.transRecordTime }</td>
			<td>${ transactionRecord.transType }</td>
			<td>${ transactionRecord.transAmount }</td>
			<td>${ transactionRecord.transCategory }</td>
			<td>${ transactionRecord.fromAccId }</td>
			<td>${ transactionRecord.toAccId }</td>
			<td>${ transactionRecord.transNote }</td>
			<td>
				<!-- Trigger the operation to edit this record. -->
				<form action="setupAddOrEditForm" method="post">
					<input type="hidden" name="transIdForEdit" value="${ transactionRecord.transRecordId }" />
					<input type="submit" value="Edit" />
				</form>
			</td>
			<td>
				<!-- Trigger the operation to delete this record. -->
				<form action="delTransaction">
					<input type="hidden" name="transIdForDel" value="${ transactionRecord.transRecordId }" />
					<input type="submit" value="Delete" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

<c:forEach var="index" begin="1" end="${ totalPages }">
	<a href="queryTransactionRecords?page=${ index }">
		<span <c:if test="${ index == currentPage }">style="color:red;"</c:if> > ${ index } </span>
	</a>
</c:forEach>

</body>
</html>