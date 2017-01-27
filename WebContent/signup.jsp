<%@page language = "java" contentType="text/html; charset=UTF-8"
	pageEncoding = "UTF-8"%>
<%@page isELIgnored = "false"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
<link  href="./css/signUp.css" rel="stylesheet" type="text/css">
<title>ユーザー登録</title>

</head>
<body>
<div id="title">
	<h1>ユーザー新規登録</h1>
</div>
<div class = "main-contents">
<c:if test = "${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var="message">
				<li><font size=5 color=#ff0000><b><c:out value = "${ message }" /></b></font></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope="session"/>
</c:if>
<form action="signup" method="post"><br />
	<p>ログインID</p>
	<p class="loginId"><label for="login_id"></label></p>
	<input name="login_id"  value="${users.login_id}" type="login_id" id="login_id">(半角英数字6文字以上20文字以下)<br>

	<p>パスワード</p>
	<p class="password"><label for="password"></label></p>
	<input name="password" type="password" id="password">(記号を含む半角文字6文字以上255文字以下)<br>

	<p>確認用パスワード</p>
	<p class="password_confirmation"><label for="password_confirmation"></label></p>
	<input name="password_confirmation" type="password" id="password_confirmation">(確認のためもう1度入力してください)<br>

	<p>名前</p>
	<p class="name"><label for="name"></label><p>
	<input name="name"  value="${users.name}" type="name" id="name">(10文字以下)<br>

	<p>所属</p>
	<div class = "select">
	<select name="branch_id" >
		<c:forEach items="${branches}" var="branch">
			<option value="${branch.id}">${branch.name}</option>
		</c:forEach>
	</select><br/>

	<p>役職</p>
	<select name="department_id" >
		<c:forEach items="${departments}" var="department">
			<option value="${department.id}">${department.name}</option>
		</c:forEach>
	</select><br/></div>
	<div class="button"><input type="submit"  value ="登録する"/><br/></div>
	</form>
	</div>
<p><a href ="userManugement">戻る</a></p>
</body>
</html>