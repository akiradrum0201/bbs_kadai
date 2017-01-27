<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  href="./css/top.css" rel="stylesheet" type="text/css">
<title>掲示板</title>
</head>
<body>
<div class="header">
<c:if test = "${ not empty message }">
	<div class = "errorMessages">
		<ul>
			<c:forEach items = "${ message }" var = "message">
				<li><font size=5 color=#ff0000><b><c:out value = "${ message }"/></b></font></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var = "message" scope = "session"/>
</c:if>
	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
		<a href="signup">登録する</a>
	</c:if>
	<c:if test="${ not empty loginUser }">
		<div class="link">
		<a id="posting" class="posting" href="posting">投稿する</a>
			<c:if test="${loginUser.department_id==1}">
				<a id="usermanugement" href="userManugement">ユーザー管理</a>
			</c:if>
		<a id="logout" href="logout">ログアウト</a></div>
	<form action="./" method="get">
		<p>日付：<input type="date" name="start_date" value="${start_date}" >～
		<input type="date" name="end_date"  value="${ end_date}">
		<p>カテゴリー：<span><input type="text"  name="findCategory" cols="20" rows="1" class="comment-box" wrap="off"></text></span><br/>
		<p><input type="submit" name="find" value="絞り込む" /></p>
		<HR size="5">
	</form>
</c:if>
</div>

<div class="main-contents">
	<div class="posting">
	<c:forEach items="${ postings }" var="posting">

			<span class="day">[投稿日時]：<c:out value="${ posting.insert_date }" /></span><br/>
			<span class="name">[名前]：<c:out value="${ posting.name }" /></span><br/>
			<span class="subject">[件名]：<c:out value="${ posting.subject }" /></span><br/>
			<span class="category" >[カテゴリー]：<c:out value="${ posting.category }" /></span><br/>
			[本文]<br/>

				<c:forEach var="postings" items="${fn:split(posting.text, '
				')}">
					<c:out value="${ postings }" /><br/>
  			 	 </c:forEach>

			<span class="date"><fmt:formatDate value="${ posting.insertDate }" pattern="yyy/MM/dd HH:mm:ss" /></span><br/>
				<c:if test="${user.department_id==2||loginUser.id==posting.user_id}">
					<form action="delete" method="post">
						<input type="hidden" name="deletePosting" value="${ posting.id }"/>
						<input type="submit" name="submit" value="削除" onClick="return confirm('本当に削除しますか？')"/>
					</form>
				</c:if>

				<c:choose >
					<c:when test="${loginUser.department_id==3&&posting.branch_id==loginUser.branch_id&&posting.department_id==4}">
						<form action="delete" method="post">
							<input type="hidden" name="deletePosting" value="${ posting.id }"/>
							<input type="submit" name="submit" value="削除" onClick="return confirm('本当に削除しますか？')"/>
						</form>
					</c:when>
				</c:choose>
				<HR size="7">

				<p><b>＜コメント＞</b></p>
				<HR size="1">

				<c:forEach items="${ comments }" var="comment">


					<c:if test= "${ posting.id == comment.id }" >
							<p><span class="name">[名前]：<c:out value="${ comment.name }" /></span><br/>
							[本文]<br/>
							<c:forEach var="comments" items="${fn:split(comment.text, '
							')}">
								<c:out value="${ comments }" /><br/>

  			 				 </c:forEach>
							<span class="date"><fmt:formatDate value="${ comment.insert_date }" pattern="yyy/MM/dd HH:mm:ss" /></span><br/></p>

								<c:if test="${loginUser.id==comment.user_id||user.department_id==2}">
									<form action="delete" method="post">
										<input type="hidden" name="deleteComment" value="${ comment.comment_id }"/>
										<input type="submit" name="submit" value="削除" onClick="return confirm('本当に削除しますか？')"/>
									</form>
								</c:if>

								<c:choose >
									<c:when test="${loginUser.department_id==3&&comment.branch_id==loginUser.branch_id&&comment.department_id==4}">
										<form action="delete" method="post">
											<input type="hidden" name="deleteComment" value="${ comment.comment_id }"/>
											<input type="submit" name="submit" value="削除" onClick="return confirm('本当に削除しますか？')"/>
										</form>
									</c:when>
								</c:choose>
								<HR size="1">
					</c:if>
				</c:forEach>
					<div class="comment"><form action="comment" method="post">
						<label for="coment">コメント(500文字以下)</label><br/>
						<input type="hidden" name="posting_id" value="${ posting.id }"/>
						<textarea name="comment" cols="60" rows="5" class="comment-box"  ></textarea><br/>
						<input type="submit"  value ="コメントする"/><br/>
					</form></div>
					<Hr Noshade size="7">
		</c:forEach>
		</div>

</div>

</body>
</html>