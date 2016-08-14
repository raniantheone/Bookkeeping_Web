<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="formOpts" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.formOpts }" />
<c:set var="typeId" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.typeId }" />
<c:set var="transToEdit" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.transToEdit }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/WEB-INF/views/SignOut.jsp" />

<!-- 
The form is used for 2 operations: add or edit transaction record.
Each operation deals with 3 types of transaction: income, expense, account transfer.
 -->
<form action="addOrEditTransaction" method="post">
	
	<!-- Indicates which record is going to be changed for an edit operation. -->
	<input type="hidden" name="transRecordId" value="${ transToEdit.transRecordId }" />
	
	<span>Amount</span><input type="text" name="transAmount" value="${ transToEdit.transAmount }" />
	<br>
	
	<!-- Indicates type of transaction, such as income, expense, account transfer. -->
	<input type="text" name="transType" value="${ typeId }"  readonly />
	<span>${ formOpts.transTypes[typeId] }</span>
	<br>
	
	<!-- Indicates category of transaction -->
	<span>Category</span><select name="transCategory">
		<c:forEach var="transCategory" items="${ formOpts.transCategories }">
			<option value="${ transCategory.key }" <c:if test="${ transCategory.key == transToEdit.transCategory }">selected</c:if> >${ transCategory.value }</option>
		</c:forEach>
	</select>
	<br>
	
	<c:if test="${ typeId != 2 }">
		<!-- Income or transfer to certain account. -->
		<span>To Account</span><select name="toAcc">
			<c:forEach var="toAccount" items="${ formOpts.userAccounts }">
				<option value="${ toAccount.key }" <c:if test="${ toAccount.key  == transToEdit.toAccId }">selected</c:if> >${ toAccount.value }</option>
			</c:forEach>
		</select>
		<br>
	</c:if>
	
	<c:if test="${ typeId != 1 }">
		<!-- Expense or transfer from certain account. -->
		<span>From Account</span><select name="fromAcc">
			<c:forEach var="fromAccount" items="${ formOpts.userAccounts }">
				<option value="${ fromAccount.key }" <c:if test="${ fromAccount.key  == transToEdit.fromAccId }">selected</c:if> >${ fromAccount.value }</option>
			</c:forEach>
		</select>
		<br>
	</c:if>
	
	<!-- Date range available: -7 ~ today(or existing record date) ~ +7 -->
	<span>Transaction Date</span><select name="transDate">
		<c:forEach var="dateOpt" items="${ formOpts.dateRange }" varStatus="loopStat">
			<option value="${ dateOpt.key }" <c:if test="${ loopStat.count > fn:length(formOpts.dateRange)/2 
			&& loopStat.count < fn:length(formOpts.dateRange)/2 + 1 }">selected</c:if> >${ dateOpt.value }</option>
		</c:forEach>
	</select>
	<br>
	
	<span>Note</span>
	<br>
	<textarea rows="2" cols="25" name="transNote">${ transToEdit.transNote }</textarea>
	<br>
	
	<input type="submit" value="Confirm" />

</form>

</body>
</html>