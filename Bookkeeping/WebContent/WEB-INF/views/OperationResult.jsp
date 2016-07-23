<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="operationSuccess" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.operationSuccess }" />
<c:set var="nextActionUrl" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.nextActionUrl }" />
<c:set var="nextActionDescription" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.nextActionDescription }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- TODO

Serve next action as well as description dynamically.

 -->

<!-- Result: success or fail -->
<c:if test="${ operationSuccess }">
	<span>Operation success</span>
</c:if>
<c:if test="${ !operationSuccess }">
	<span>Operation fail</span>
</c:if>


<!-- Next action -->
<form action="${ nextActionUrl }" method="post">
	<input type="submit" value="${ nextActionDescription }" />
</form>

</body>
</html>