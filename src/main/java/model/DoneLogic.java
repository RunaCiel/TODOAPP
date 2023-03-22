package model;

import dao.ToDoDAO;

public class DoneLogic {
	
	public boolean execute(User user, int taskId) {
		
		ToDoDAO toDoDAO = new ToDoDAO();
		boolean result = toDoDAO.done(user, taskId);
		
		if (result) {
			return true;
			
		} else {
			return false;
		}
		
	}

}
