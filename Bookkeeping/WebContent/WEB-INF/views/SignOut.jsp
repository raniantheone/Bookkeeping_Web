<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="user" scope="page" value="${ sessionScope.DataFlowCarrier.user }" />
<c:if test="${ not empty user }">
	<form action="SignOut" method="post">
		<input type="submit" value="Sign Out" />
	</form>
</c:if>
