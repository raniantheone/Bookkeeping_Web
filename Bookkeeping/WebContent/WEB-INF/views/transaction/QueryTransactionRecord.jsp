<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="formOpts" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.formOpts }" />
<c:set var="transTypeOpts" scope="page" value="${ formOpts.transTypes }" />
<c:set var="userAccounts" scope="page" value="${ formOpts.userAccounts }" />
<c:set var="transCategories" scope="page" value="${ formOpts.transCategories }" />

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

<!-- Drafting search criteria form -->
<form action="queryTransactionRecords" method="post">

	<span>Start date</span><input type="text" name="criteria_start_date" placeholder="YYYY-MM-DD" />
	<br>
	
	<span>End date</span><input type="text" name="criteria_end_date" placeholder="YYYY-MM-DD" />
	<br>
	
	<span>Type</span>
	<select name="criteria_trans_type">
		<option value="">Pick below or leave empty</option>
		<c:forEach var="transOpt" items="${ transTypeOpts }" >
			<option value="${ transOpt.key }">${ transOpt.value }</option>
		</c:forEach>
	</select>
	<br>
	
	<span>Category</span>
	<select name="criteria_category">
		<option value="">Pick below or leave empty</option>
		<c:forEach var="categoryOpt" items="${ transCategories }">
			<option value="${ categoryOpt.key }">${ categoryOpt.value }</option>
		</c:forEach>
	</select>
	<br>
	
	<span>From Account</span>
	<select name="criteria_from_acc">
		<option value="">Pick below or leave empty</option>
		<c:forEach var="accOpt" items="${ userAccounts }">
			<option value="${ accOpt.key }">${ accOpt.value }</option>
		</c:forEach>
	</select>
	<br>
	
	<span>To Account</span>
	<select name="criteria_to_acc">
		<option value="">Pick below or leave empty</option>
		<c:forEach var="accOpt" items="${ userAccounts }">
			<option value="${ accOpt.key }">${ accOpt.value }</option>
		</c:forEach>
	</select>
	<br>
	
	<input type="submit" value="Search transaction records" />
	
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
			<td>${ transTypeOpts[transactionRecord.transType] }</td>
			<td>${ transactionRecord.transAmount }</td>
			<td>${ transCategories[transactionRecord.transCategory] }</td>
			<td>${ userAccounts[transactionRecord.fromAccId] }</td>
			<td>${ userAccounts[transactionRecord.toAccId] }</td>
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

<!-- 10 page per group: 1~10,11~20,21~30...etc -->
<c:set var="currentPageGroupIndex" scope="page" value="${ (currentPage - 1) - ((currentPage - 1) % 10) }" /><!-- start from 0 -->
<c:if test="${ currentPage > 10 }">
	<a href="queryTransactionRecords?page=<fmt:formatNumber value="${ currentPageGroupIndex }" minFractionDigits="0" maxFractionDigits="0"/>">
		<span> Previous </span>
	</a>
</c:if>
<c:forEach var="index" begin="1" end="${ totalPages }">
	<c:if test="${ ((index - 1) - ((index - 1) % 10)) == currentPageGroupIndex }">
		<a href="queryTransactionRecords?page=${ index }">
			<span <c:if test="${ index == currentPage }">style="color:red;"</c:if> > ${ index } </span>
		</a>
	</c:if>
</c:forEach>
<c:set var="lastPageGroupIndex" scope="page" value="${ (totalPages - 1) - ((totalPages - 1) % 10) }" />
<c:if test="${ lastPageGroupIndex > currentPageGroupIndex }">
	<a href="queryTransactionRecords?page=<fmt:formatNumber value="${ currentPageGroupIndex + 11 }" minFractionDigits="0" maxFractionDigits="0" />">
		<span> Next </span>
	</a>
</c:if>

</body>
</html>