<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<form method="post" action="./Login?exec=login">
<p style="color:red;">${ errmsg }</p>
<p>ユーザーID：<input type="text" name="id" value="${param.id}"/></p>
<p>パスワード：<input type="password" name="pass" value="${param.pass}"/></p>
<p><input type="submit" value="ログイン"/></p>
</form>
</body>
</html>