<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entity.UserEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">

<script type="text/javascript">
	$(function() {
		const MemberRegistration = "会員登録";
		const MemberUpdate = "会員更新";
		const MemberDelete = "会員削除";

		//	タイトル表示
		if ("${proc}" == "new") {
			document.title = MemberRegistration
			$("h1").text(MemberRegistration)
		} else if ("${proc}" == "update") {
			document.title = MemberUpdate
			$("h1").text(MemberUpdate)
		}else if ("${proc}" == "delete") {
			document.title = MemberDelete
			$("#confirm_btn").text("削除");
			$("h1").text(MemberDelete)
		}
	});
</script>
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
	<h1></h1>
	<form method="POST" action="./Confirm">
		<div>ユーザーID:<span><c:out value="${user.idLoginUser}"></c:out></span></div>
		<div>氏名:<span><c:out value="${user.meiUser}"></c:out></span></div>
		<div>年齢:<span><c:out value="${user.age}"></c:out></span></div>
		<div>性別:<span><%=displaySeibetsu %></span></div>
		<button type="submit" id="confirm_btn">登録</button>
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