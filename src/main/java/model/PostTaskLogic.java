package model;

import dao.ToDoDAO;

public class PostTaskLogic {

	public void execute(ToDo toDo) {
		
		ToDoDAO dao = new ToDoDAO();
		dao.create(toDo);
		
	}
	
}
