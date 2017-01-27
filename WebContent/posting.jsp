<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored = "false"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  href="./css/posting.css" rel="stylesheet" type="text/css">
<title>新規投稿</title>
</head>
<body>
<div id="title">
	<h1>新規投稿</h1>
</div>
<div class="form-area">
	<c:if test = "${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var="message">
				<li><font size=5 color=#ff0000><b><c:out value = "${ message }"/></b></font></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope="session"/>
</c:if>
	<form action="posting" method="post">
		<table border="1">
			<tr>
				<th>件名</th>
				<td><input type="text" name="subject" size="40">(50文字以下で入力してください)</td>
			</tr>
			<tr>
				<th>カテゴリー</th>
				<td><input type="text" name="category" size="40">(10文字以下で入力してください)</td>
			</tr>
			<tr>
				<th class="text">本文</th>
				<td><textarea name="text" cols="60" rows="15" class="posting-box" "MSG" ></textarea>(1000文字以下で入力してください)</td>
			</tr>
		</table>
	<p><input type="submit"  value="投稿する"></p>
	</form>
	<p><a href="./">戻る</a></p>
</div>
</body>
</html>