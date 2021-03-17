<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.UserEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	UserEntity user = (UserEntity) request.getAttribute("user");

	String displaySeibetsu = "";
	if(user.getSeibetu().equals("0")){
		displaySeibetsu = "男";
	}else if(user.getSeibetu().equals("1")){
		displaySeibetsu = "女";
	}else{
		displaySeibetsu = user.getSeibetuCustom();
	}
%>
<body>
	<form method="POST" action="./Confirm">
		<div>ユーザーID:<span><c:out value="${user.idLoginUser}"></c:out></span></div>
		<div>氏名:<span><c:out value="${user.meiUser}"></c:out></span></div>
		<div>年齢:<span><c:out value="${user.age}"></c:out></span></div>
		<div>性別:<span><%=displaySeibetsu %></span></div>
		<button type="submit">登録</button>
		<button type="button" onclick="history.back()">戻る</button>

		<input type="hidden" id="proc"  name="proc" value="<c:out value="${proc}" default="" />">
  		<input type="hidden" name="idUser" value="<c:out value="${user.idUser}" default="" />">
  		<input type="hidden" name="idLoginUser" value="<c:out value="${user.idLoginUser}" default="" />">
  		<input type="hidden" name="password" value="<c:out value="${user.password}" default="" />">
  		<input type="hidden" name="meiUser" value="<c:out value="${user.meiUser}" default="" />">
  		<input type="hidden" name="age" value="<c:out value="${user.age}" default="" />">
  		<input type="hidden" name="seibetsu" value="<c:out value="${user.seibetu}" default="" />">
  		<input type="hidden" name="custom" value="<c:out value="${user.seibetuCustom}" default="" />">
	</form>

</body>
</html>