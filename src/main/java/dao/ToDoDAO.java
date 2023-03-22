package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.ToDo;
import model.User;

public class ToDoDAO {
	
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/todoapp_db";
	private final String DB_USER = "root";
	private final String DB_PAS = "pass";
	
	public ArrayList<ToDo> findTask(User user) {
		ArrayList<ToDo> toDoList = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PAS);
			String sql = "SELECT task_id, task, task_date, done FROM todo WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, user.getId());
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				int taskId = rs.getInt("task_id");
				String task = rs.getString("task");
				Date date = rs.getDate("task_date");
				int done = rs.getInt("done");
				
				ToDo toDo = new ToDo(task, date, done, taskId);
				toDoList.add(toDo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return toDoList;
	}		
	
	public boolean create(ToDo toDo) {
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PAS)) {
			
			String sql = "INSERT INTO TODO(user_id, task, task_date) VALUES(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			java.sql.Date sqlDate = new java.sql.Date(toDo.getDate().getTime());
			
			pStmt.setInt(1, toDo.getId());
			pStmt.setString(2, toDo.getTask());
			pStmt.setDate(3, sqlDate);
			
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean done(User user, int taskId) {
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PAS)) {
			
			String sql = "UPDATE TODO SET done = 1 WHERE task_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, taskId);
			
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			} 
		} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}

	public boolean delete(User user, int taskId) {
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PAS)) {
			
			String sql = "DELETE FROM TODO WHERE task_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, taskId);
			
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			} 	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
