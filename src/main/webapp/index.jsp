<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>F-Reversi</title>
</head>
<body>
    <h1>Hello new Reversi World.</h1>
<%--<form action="<%=request.getContextPath()%>/Login" method="post"> --%>
    <form action="/Login" method="post">
        name：<input type="text" name="name"><br>
        <input type="submit" value="ログイン">
    </form>
</body>
</html>