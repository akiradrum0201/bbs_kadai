<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  href="./css/setting.css" rel="stylesheet" type="text/css">
<title>ユーザー編集</title>
</head>
<body>
<div id="title">
	<h1>ユーザー編集</h1>
</div>
<div class = "main-contents">
<c:if test = "${ not empty errorMessage }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ errorMessage }" var = "message">
				<li><font size=5 color=#ff0000><b><c:out value = "${ message }"/></b></font></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessage" scope = "session"/>
</c:if>
<form action="setting" method="post">
	<p>ログインID</p>
	<p class="loginId"><label for="login_id" ></label></p>
	<input name="login_id" type="login_id"  value="${user.login_id}" id="login_id" >(半角英数字6文字～20文字以下)<br/>

	<p>パスワード</p>
	<p class="password"><input type=hidden name="id"  value="${ user.id}" ></p>
	<label for="password" ></label>
	<input name="password"   type="password" id="password">(記号を含む半角文字6文字以上～255文字以下)<br/>

	<p>確認用パスワード</p>
	<p class="password_confirmation"><label for="password_confirmation"></label></p>
	<input name="password_confirmation"   type="password" id ="password_confirmation"/>(確認のためもう1度入力してください)<br/>

	<p>名前</p>
	<p class="name"><input name="name"  type="name" value="${user.name}" id="name">(10文字以下)<br/>

	<p>所属</p>
	<div class = "select">
	<label for="branch_id" ></label>
	<select name="branch_id" >
		<c:forEach items="${branch}" var="branch">
			<c:if test= "${ user.branch_id == branch.id }">
				<option value="${branch.id}" selected>${branch.name}</option >
			</c:if>
			<c:if test= "${ user.branch_id != branch.id }">
				<option value="${branch.id}">${branch.name}</option >
			</c:if>
		</c:forEach>
	</select><br/>

	<p>役職</p>
	<label for="department_id" ></label>
	<select name="department_id" >
		<c:forEach items="${department}" var="department">
			<c:if test= "${ user.department_id ==department.id }">
				<option value="${department.id}" selected>${department.name}</option >
			</c:if>
			<c:if test= "${ user.department_id !=department.id }">
				<option value="${department.id}">${department.name}</option >
			</c:if>
			</c:forEach>
	</select><br/></div>

	<div class="button"><p><input type="submit" value="登録する"/><br/></p></div>
	</form>
	</div>
<p><a href="userManugement">戻る</a></p>


</body>
</html>