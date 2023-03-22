<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String errorMsg = (String) request.getAttribute("errorMsg"); 

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDoアプリ</title>
</head>
<body>
	<h1>ようこそ</h1>
	<p>名前を入力してください</p>
	<form action="/todoapp/LoginServlet" method="post">
	<input type="text" name="userName">
	<input type="submit" value="送信">
	</form>
<% if(errorMsg != null) { %>
	<p><%= errorMsg %></p>
<% } %>

</body>
</html>