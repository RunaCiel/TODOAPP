package model;

import java.util.ArrayList;

import dao.ToDoDAO;

public class GetTaskListLogic {
	public ArrayList<ToDo> execute(User user) {
		ToDoDAO toDoDAO = new ToDoDAO();
		ArrayList<ToDo> toDoList = toDoDAO.findTask(user);
		return toDoList;
	}
}
