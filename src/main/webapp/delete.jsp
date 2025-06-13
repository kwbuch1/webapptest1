<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jp.co.akkodis.emp.dto.EmployeeDto,java.sql.Timestamp"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		String manSelected = "";
		String womanSelected = "";
	%>
	<%
		EmployeeDto updateEmployee = (EmployeeDto)request.getAttribute("deleteEmployee");
	
	%>
	<header>
		<h1>従業員情報削除ページ</h1>
	</header>
	<main>
		<p class="message"><c:out value="${message}" /></p>
		<form action="delete" method="post">
			<table border="1">
				<tr>
					<td>従業員番号</td>
					<td><input type="text" name="empno" value="${deleteEmployee.empNo}"></td>
				</tr>
				<tr>
					<td>従業員名</td>
					<td><input type="text" name="empname" value="${deleteEmployee.empName}"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name="btn" value="DeleteEmp">
						<input type="submit" value="削除">
					</td>
				</tr>
			</table>
		</form>
		<p><a href="controller">従業員検索ページ</a></p>
	</main>
</body>
</html>