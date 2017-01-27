<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  href="./css/login.css" rel="stylesheet" type="text/css">

<title>ログイン</title>
</head>
<body>
<div class="main-contents">
<div id="title">
	<h1>ログイン</h1>
</div>
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${ errorMessages }" var="message">
				<li><font size=5 color=#ff0000><b><c:out value = "${ message }"/></b></font></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>
<c:if test="${not empty message }">
	<c:out value="${message }"/>
</c:if>

<form action="login" method="post"><br/>
	<p>ログインID</p>
	<p class="loginId"><label for="login_id" ></label></p>
	<input name="login_id" type="loginId" id="login_id"><br>

	<p>パスワード</p>
	<p class="password"><label for="password"></label><p>
	<input name="password" type="password" id="password" ><br>
	<p class="submit"><input name="submit" type="submit" value="ログイン"><br></p>


</form>
</div>
</body>
</html>