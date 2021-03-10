<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.UserEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script>
	function upDelUser(kbn, id) {
		window.open('./Member?proc=' + kbn + '&id_user=' + id);
	}
</script>

<title>会員検索</title>
</head>
<body>
	<h1>会員検索</h1>
	<table border="1">
		<tr>
			<th>No</th>
			<th>ログインID</th>
			<th>氏名</th>
			<th>年齢</th>
			<th>性別</th>
			<th></th>
		</tr>
		<c:forEach var="list" items="${userList}" varStatus="status">
			<tr>
				<td>${list.getIdUser()}</td>
				<td>${list.getIdLoginUser()}</td>
				<td>${list.getMeiUser()}</td>
				<td>${list.getAge()}</td>

				<c:if test="${list.getSeibetu().isEmpty()}" var="customFlag" />
				<c:if test="${customFlag}">
					<td>${list.getSeibetuCustom()}</td>
				</c:if>
				<c:if test="${!customFlag}">
					<c:if test="${list.getSeibetu() == '0'}" var="seibetuFlag" />
					<c:if test="${seibetuFlag}">
						<td>男</td>
					</c:if>
					<c:if test="${!seibetuFlag}">
						<td>女</td>
					</c:if>
				</c:if>
				<td><input type="button" value="更新"
					onclick="upDelUser('fetchUpdate', ${list.getIdUser()});"> <input
					type="button" value="削除"
					onclick="upDelUser('fetchDelete', ${list.getIdUser()});"></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>