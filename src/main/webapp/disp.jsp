<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style>
		.message {
			color: #FF0000;
		}
	</style>
	<title>従業員検索ページ </title>
</head>
<body>
	<%
		// 文字コードの設定
		request.setCharacterEncoding("UTF-8");
	%>
	<header><h1> 従業員検索ページ </h1></header>
	<main>
		<form action="controller" method="post">
			<p>検索したい従業員ID を入力して下さい </p>
			<p>
				<input type="text" name="paramId">
				<input type="hidden" name="btn" value="IdSearch">
				<input type="submit" value="検索">
			</p>
		</form>
		<form action="controller" method="post">
			<p>検索したい年齢を入力して下さい </p>
			<p>
				<input type="text" name="paramAge1"> 歳から
				<input type="text" name="paramAge2"> 歳まで
				<input type="hidden" name="btn" value="AgeSearch">
				<input type="submit" value="検索">
			</p>
		</form>
		<c:if test="${not empty requestScope.message}">
			<p class="message">${requestScope.message}</p>
		</c:if>
		<c:if test="${not empty requestScope.empList}">
			<h2> 検索結果 </h2>
			<table border="1">
				<tr>
					<th>従業員ID</th>
					<th>氏名</th>
					<th>性別</th>
					<th>誕生日</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach var="emp" items="${requestScope.empList}">
					<tr>
						<td><c:out value="${emp.empNo}" /></td>
						<td><c:out value="${emp.empName}" /></td>
						<td><c:out value="${emp.genderName}" /></td>
						<td><c:out value="${emp.birthday}" /></td>
						<td>
							<form action="update" method="get">
								<input type="hidden" name="empNo" value="${emp.empNo}">
								<input type="submit" value="更新">
							</form>
						</td>
						<td>
							<form action="delete" method="get">
								<input type="hidden" name="empNo" value="${emp.empNo}">
								<input type="submit" value="削除">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</main>
</body>
</html>