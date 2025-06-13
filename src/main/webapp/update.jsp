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
		EmployeeDto updateEmployee = (EmployeeDto)request.getAttribute("updateEmployee");
	
		if(updateEmployee.getGenderCd().equals("1")){
			manSelected = "selected";
		}else{
			womanSelected = "selected";
		}
		
		String optionTags = (String)request.getAttribute("optionTags");
		Timestamp ts = updateEmployee.getBirthday();
	%>
	<header>
		<h1>従業員情報更新ページ</h1>
	</header>
	<main>
		<p class="message"><c:out value="${message}" /></p>
		<form action="update" method="post">
			<table border="1">
				<tr>
					<td>従業員番号</td>
					<td><input type="text" name="empno" value="${updateEmployee.empNo}"></td>
				</tr>
				<tr>
					<td>従業員名</td>
					<td><input type="text" name="empname" value="${updateEmployee.empName}"></td>
				</tr>
				<tr>
					<td>性別</td>
					<td>
						<select name="gender">
							<option value="1" <%=manSelected %>>男性</option>
							<option value="2" <%=womanSelected %>>女性</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>誕生日</td>
					<td>
						<input type="text" name="year" size="5" value="<%=ts.getYear()+ 1900 %>">年
						<input type="text" name="month" size="5" value="<%=ts.getMonth()+ 1 %>">月
						<input type="text" name="day" size="5" value="<%=ts.getDate() %>">日
					</td>
				</tr>
				<tr>
					<td>出身地</td>
					<td>
						<select name="birthPlace">
							<%=optionTags %>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name="btn" value="UpdateEmp">
						<input type="submit" value="更新">
					</td>
				</tr>
			</table>
		</form>
		<p><a href="controller">従業員検索ページ</a></p>
	</main>
</body>
</html>