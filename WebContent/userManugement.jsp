<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  href="./css/usermanugement.css" rel="stylesheet" type="text/css">
<title>ユーザー管理</title>
</head>
<body>
<div id="title">
	<h1>ユーザー管理</h1>
</div>
<c:if test = "${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items = "${ errorMessages }" var="message">
				<li><font size=5 color=#ff0000><b><c:out value = "${ message }"/></b></font></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "errorMessages" scope = "session"/>
</c:if>
<p><a href="signup"><font size="5">新規登録</font></a></p>
<div class="users">
	<table border=”1″ width= 1000 height=300>
		<tr>
			<th>名前</th>
			<th>ログインID</th>
			<th>所属</th>
			<th>役職</th>
			<th>アカウント停止</th>
			<th>停止状況</th>
			<th>ユーザー編集</th>
		</tr>

			<c:forEach items="${ users }" var="user">
				<tr>
					<td><c:out value="${ user.name }" /></td>
					<td><c:out value="${ user.login_id }" /></td>
						<td>
							<c:forEach items="${branch}" var="branch">
								<c:if test="${ user.branch_id == branch.id }">
								<c:out value="${ branch.name }" />
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach items="${department}" var="department">
								<c:if test="${ user.department_id == department.id }">
									<c:out value="${ department.name }" />
								</c:if>
							</c:forEach>
						</td>
					<td>
					<c:if test="${user.is_stopped==0}">
						<form action="userManugement" method="post">
							<c:if test="${loginUser.id!=user.id}">
								<input type="hidden" name="is_stopped" value="${ user.id }"/>
								<input type="submit" value="停止"  onClick="return confirm('本当に停止しますか？')"/>
							</c:if>
						</form>
					</c:if>
					<c:if test="${user.is_stopped==1}">
						<form action="userManugement" method="post">
							<input type="hidden" name="resurrection" value="${ user.id }"/>
							<input type="submit" value="復活"  onClick="return confirm('本当に復活しますか？')"/>
						</form>
					</c:if>
					</td>

					<td><c:if test="${ user.is_stopped ==1}"><font  color="red">停止中</font></c:if></td>
					<td><form action="setting" method="post">
					<input type="hidden" name="setting" value="${ user.id }"/>
					<c:if test="${loginUser.id!=user.id}">
					<a href="setting?id=${ user.id }">編集</a>
					</c:if>
					</form></td>
					</tr>
			</c:forEach>
	</table>
</div>
<p><a href ="./">戻る</a></p>

</body>
</html>