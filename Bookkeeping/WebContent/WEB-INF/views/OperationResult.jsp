<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="operationSuccess" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.operationSuccess }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- Result: success or fail -->
<span>${ operationSuccess }</span>

<!-- Next action -->
<form action="queryTransactionRecords" method="post">
	<input type="submit" value="Back to transaction records" />
</form>

</body>
</html>