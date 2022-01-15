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
	<script type="text/javascript">
	    function placePiece(index) {
		    location.href = "/Reversi?index="+index
        }
    </script>
	<table id="field">
		<c:forEach var="grid" items="${game.judge.table.grids}">
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
				</c:if></td>
		</c:forEach>
	</table>
	<p><c:out value="${game.judge.getCurrentPlayer().name}"/></p>
    <form action="/Main">
        <input type="submit" value="戻る">
    </form>
</body>

</html>