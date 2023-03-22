<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.ToDo,java.sql.*,java.util.ArrayList" %>
<%

User user = (User) session.getAttribute("loginUser");
ArrayList<ToDo> toDoList = (ArrayList<ToDo>) request.getAttribute("toDoList");
String errorMsg = (String) request.getAttribute("errorMsg");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDoアプリ</title>
</head>
<body>
	<h1><%= user.getName() %>さんのToDo</h1>
	
	<form action="/todoapp/MainServlet" method="post">
		<label>内容<input type="text" name="task"></label>
		<label>期日<input type="date" name="taskDate"></label>
		<input type="submit" value="作成">
	</form>
	
	<% if(errorMsg != null) { %>
	<p><%= errorMsg %> </p>
	<% } %>
	
	<ul>
		<% for(ToDo toDo : toDoList) { %>
		
			<% if(toDo.getDone() == 1) {%>
			<li><del><%= toDo.getTask() %></del>
				<del><span style="display:inline; margin: 0px 10px; font-size: 12px;"><%= toDo.getDate() %></span></del>
				<form action="/todoapp/Delete" method="post" style="display:inline;">
				<input type="hidden" name="taskId" value="<%= toDo.getTaskId() %>">
				<input type="submit" value="削除">
				</form>
			</li>
			
			<% } else { %>
			<li><%= toDo.getTask() %>
				<span style="display:inline-block; margin: 0px 10px; font-size: 12px;"><%= toDo.getDate() %></span>
				<form action="/todoapp/Done" method="post" style="display:inline;">
				<input type="hidden" name="taskId" value="<%= toDo.getTaskId() %>">
				<input type="submit" value="完了">
				</form>
				
				<form action="/todoapp/Delete" method="post" style="display:inline;">
				<input type="hidden" name="taskId" value="<%= toDo.getTaskId() %>">
				<input type="submit" value="削除">
				</form>
			</li>
			<% } %>
			
		<% } %>
	</ul>
	
	<a href="/todoapp/index.jsp">戻る</a>
	
</body>
</html>