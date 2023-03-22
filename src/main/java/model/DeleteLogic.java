package model;

import dao.ToDoDAO;

public class DeleteLogic {
	
public boolean execute(User user, int taskId) {
		
		ToDoDAO toDoDAO = new ToDoDAO();
		boolean result = toDoDAO.delete(user, taskId);
		
		if (result) {
			return true;
			
		} else {
			return false;
		}
		
	}

}
