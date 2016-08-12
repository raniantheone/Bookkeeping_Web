<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" scope="page" value="${ sessionScope.DataFlowCarrier.flowResults.user }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${ empty user }">
	<form action="EntryPage" method="post">
	Account: <input type="text" name="userAccount" />
	<br>
	Password: <input type="password" name="userPassword" />
	<br>
	<input type="submit" value="Sign In" />
	</form>
</c:if>

<c:if test="${ not empty user }">
	<span>Welcome ${ user.userName }</span>	
</c:if>

</body>
</html>