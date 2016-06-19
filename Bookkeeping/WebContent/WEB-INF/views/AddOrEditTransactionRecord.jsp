<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
The form is used for 2 operations: add or edit transaction record.
Each operation deals with 3 types of transaction: income, expense, account transfer.
 -->
<form>
	
	<!-- Indicates which record is going to be changed for an edit operation. -->
	<input type="hidden" name="transRecordId" value="" />
	
	<input type="text" name="transAmount" />
	
	<!-- Indicates type of transaction, such as income, expense, account transfer. -->
	<input type="text" name="transType" readonly />
	
	<!-- Income or transfer to certain account. -->
	<select name="toAcc">
	</select>
	
	<!-- Expense or transfer from certain account. -->
	<select name="fromAcc">
	</select>
	
	<!-- Date range available: -7 ~ today(or existing record date) ~ +7 -->
	<select name="transDate">
	</select>
	
	<textarea rows="2" cols="25" name="transNote">
	</textarea>
	
	<input type="submit" value="Confirm" />

</form>

</body>
</html>