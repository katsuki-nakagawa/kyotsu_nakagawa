<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entity.UserEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%
	UserEntity user = (UserEntity) request.getAttribute("user");
%>
<title>メニュー画面</title>
</head>
<body>
	<h1>会員メニュー</h1>
	<div>ようこそ${sessionScope.userinfo.meiUser}様</div>
	<form method="GET" action="./Menu" target="_new">
		<div>
			<input type="hidden" name="proc" value="new"> <input
				type="submit" value="会員登録">
		</div>
	</form>
	<form method="POST" action="./MemberList" target="_new"></form>
		<div>
			<input type="submit" value="会員検索">
		</div>
	<form method="GET" action="./Menu">
		<div>
			<input type="submit" value="ログアウト">
		</div>
	</form>
</body>
</html>