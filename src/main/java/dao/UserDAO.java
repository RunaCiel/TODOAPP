package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/todoapp_db";
	private final String DB_USER = "root";
	private final String DB_PAS = "pass";
	
	public User findByLogin(User user) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PAS);
			String sql = "SELECT user_id, user_name FROM todo_user WHERE user_name = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, user.getName());
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("user_id");
				user.setId(id);
			} else {
				sql = "INSERT INTO todo_user(user_name) VALUES(?)";
				pStmt = conn.prepareStatement(sql);
				
				pStmt.setString(1, user.getName());
				
				int result = pStmt.executeUpdate();
				
				if (result != 1) {
					return null;
					
				} else {
					sql = "SELECT user_id, user_name FROM todo_user WHERE user_name = ?";
					pStmt = conn.prepareStatement(sql);
					
					pStmt.setString(1, user.getName());
					rs = pStmt.executeQuery();
					rs.next();
					int id = rs.getInt("user_id");
					user.setId(id);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}		
}
