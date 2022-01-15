<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>F-Reversi</title>
<link rel="stylesheet" type="text/css"
	href="/css/table.css">
</head>
<body>
    <p><c:out value="${loginPlayer.name}"/>のロビー</p>
    <form action="/NewGame">
        <input type="submit" value="新しいゲーム">
    </form>
    <p><a href="/Main">リロード</a>
    <c:forEach var="aGame" items="${gameList}">
        <table id="field">
		    <c:forEach var="grid" items="${aGame.judge.table.grids}">
			    <c:if test="${grid.column == 0}"><tr></c:if>
 			    <td>
 			        <c:if test="${grid.piece.color == -1}">
			            <font color="black">×</font>
			        </c:if> <c:if test="${grid.piece.color == 1}">
			            <font color="white">●</font>
			        </c:if> <c:if test="${grid.piece.color == 2}">
				        <font color="black">●</font>
			        </c:if> <c:if test="${grid.piece.color == 3}">
			            <font color="red">●</font>
			        </c:if> <c:if test="${grid.piece.color == 4}">
			            <font color="blue">●</font>
			        </c:if>
			    </td>
		    </c:forEach>
	    </table>
	    <form action="/Reversi" method="post">
		    <input type="submit" name="${aGame.id}" value="遊ぶ">
		</form>
    </c:forEach>
</body>
</html>