<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>従業員新規登録ページ</title>
</head>
<body>
	<header>
		<h1>従業員新規登録ページ</h1>
	</header>
	<main>
 		<p class="message"><c:out value="${message}"/></p>
		<form action="insert" method="post">
			<table border="1">
				<tr>
					<td>従業員番号</td>
					<td><input type="text" name="empno"></td>
				</tr>
				<tr>
					<td>従業員名</td>
					<td><input type="text" name="empname"></td>
				</tr>
				<tr>
					<td>性別</td>
					<td>
						<select name="gender">
							<option value="1">男性</option>
							<option value="2">女性</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>誕生日</td>
					<td><input type="text" name="year" size="5">年
						<input type="text" name="month" size="5">月
						<input type="text" name="day" size="5">日
					</td>
				</tr>
								<tr>
					<td>出身地</td>
					<td>
						<select name="birthPlace">
							<option value="01">北海道</option>
							<option value="02">青森県</option>
							<option value="03">秋田県</option>
							<option value="04">岩手県</option>
							<option value="05">宮城県</option>
							<option value="06">山形県</option>
							<option value="07">福島県</option>
							<option value="08">茨城県</option>
							<option value="09">群馬県</option>
							<option value="10">栃木県</option>
							<option value="11">埼玉県</option>
							<option value="12">東京都</option>
							<option value="13">千葉県</option>
							<option value="14">神奈川県</option>
							<option value="15">山梨県</option>
							<option value="16">長野県</option>
							<option value="17">新潟県</option>
							<option value="18">福井県</option>
							<option value="19">富山県</option>
							<option value="20">石川県</option>
							<option value="21">静岡県</option>
							<option value="22">岐阜県</option>
							<option value="23">愛知県</option>
							<option value="24">三重県</option>
							<option value="25">滋賀県</option>
							<option value="26">京都府</option>
							<option value="27">大阪府</option>
							<option value="28">兵庫県</option>
							<option value="29">奈良県</option>
							<option value="30">和歌山県</option>
							<option value="31">鳥取県</option>
							<option value="32">島根県</option>
							<option value="33">岡山県</option>
							<option value="34">広島県</option>
							<option value="35">山口県</option>
							<option value="36">香川県</option>
							<option value="37">愛媛県</option>
							<option value="38">徳島県</option>
							<option value="39">高知県</option>
							<option value="40">福岡県</option>
							<option value="41">佐賀県</option>
							<option value="42">長崎県</option>
							<option value="43">大分県</option>
							<option value="44">熊本県</option>
							<option value="45">宮崎県</option>
							<option value="46">鹿児島県</option>
							<option value="47">沖縄県</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="hidden" name="btn" value="InsertEmp">
						<input type="submit" 登録
					</td>
				</tr>
			</table>
		</form>
		<p><a href="controller">従業員検索ページへ</a></p>
	</main>
</body>
</html>