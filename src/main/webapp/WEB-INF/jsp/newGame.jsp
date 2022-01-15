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
</head>
<body>
    <p>New Game ...</p>
    <form action="/NewTable" method="post">
        Max Column :<input type="number" name="maxColumn"><br>
        Max Row :<input type="number" name="maxRow"><br>
        <input type="submit" value="New Table"><br>
    </form>
	<script type="text/javascript">
	    function placePiece(index) {
		    location.href = "/NewGrid?index="+index
        }
    </script>
    <table id="field">
		<c:forEach var="grid" items="${newGame.judge.table.grids}">
			<c:if test="${grid.column == 0}">
				<tr>
			</c:if>
 			<td onclick="placePiece(<c:out value="${grid.index}"/>)">
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
	<p>Player List</p>
	<c:forEach var="player" items="${newGame.judge.players}">
	    <p><c:out value="${player.name}"/></p>
	</c:forEach>
	<form action="/NewPlayer" method="post">
	    <input type="submit" name="newPlayer" value="+">
	    <input type="submit" name="removePlayer" value="-">
	    <br>
	</form>
	<form action="/NewGame" method="post">
	    <br>
	    <input type="submit" name="newGame" value="Instance!">
	    <input type="submit" name="back" value="back">
	</form>
</body>
</html>