<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- Add transaction record: income, expense, or account transfer. -->
<form action="">

	<select name="transType">
	</select>
	
	<input type="submit" value="Add Transaction Record" />
	
</form>

<table>
	<tr>
		<th></th>
	</tr>
	<tr>
		<td>
			<!-- Trigger the operation to edit this record. -->
			<form action=""></form>
		</td>
		<td>
			<!-- Trigger the operation to delete this record. -->
			<form action=""></form>
		</td>
	</tr>
</table>

</body>
</html>