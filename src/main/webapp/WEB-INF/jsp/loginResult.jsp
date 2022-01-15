<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>F-Reversi</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty loginPlayer.getName()}">
			<p>ログインに成功しました</p>
			<p>ようこそ<c:out value="${loginPlayer.getName()}" />さん</p>
			<a href="/Main">ゲーム画面へ</a>
	    </c:when>
		<c:otherwise>
		    <p>ログインに失敗しました</p>
		    <a href="/">TOPへ</a>
		</c:otherwise>
	</c:choose>
</body>
</html>