<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entity.UserEntity"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<meta charset="UTF-8">



<%
	String proc = "";
	if (request.getAttribute("proc") != null) {
		proc = request.getAttribute("proc").toString();
	}
	proc = request.getAttribute("proc").toString();
%>


<script type="text/javascript">
	$(function() {
		const proc =$('input[name="proc"]').val();
		console.log(proc);

		const MemberRegistration ="会員登録";

		//	タイトル表示
		if (proc == "new"){
			document.title = MemberRegistration
			$("h1").text(MemberRegistration)
		}


		/**
		*
		* 性別カスタム入力
		*/
		//初期表示は隠す
		$("#custom").hide();
		// 性別選択でカスタムならテキストボックスを表示する
		$("#seibetsu").on("click", function(){
			let selectSeibetsu = $("#seibetsu").val();
			console.log(selectSeibetsu);

			if (parseInt(selectSeibetsu) === 2) {
				$("#custom").show();
			} else {
				$("#custom").hide();
			}
		});


	});
</script>



<title></title>
</head>
<body>

	<h1></h1>
	<form method="POST" action="./Member">
		<input type="hidden" name="proc" value="${proc}">
		<div>
			<label for="idLoginUser">ログインユーザーID</label>
			<input type="text" name="idLoginUser" id="idLoginUser">
		</div>
		<div>
			<label for="password">パスワード</label>
			<input type="password" name="password" id="password">
		</div>
		<div>
			<label for="meiUser">ユーザー名</label>
			<input type="text" name="meiUser" id="meiUser">
		</div>
		<div>
			<label for="age">年齢</label>
			<input type="number" name="age" id="age">
		</div>
		<div>
			<label for="seibetsu">性別</label>
			<select name="seibetsu" id="seibetsu">
				<option value="0">男</option>
				<option value="1">女</option>
				<option value="2">カスタム</option>
			</select>
			<input type="text" name="custom" id="custom">
		</div>
		<div>
			<button type="submit" id="confirmationBtn">確認</button>
		</div>

	</form>


</body>
</html>