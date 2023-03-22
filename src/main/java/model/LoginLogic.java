package model;

import dao.UserDAO;

public class LoginLogic {
	public User execute(User user) {
		UserDAO userDao = new UserDAO();
		user = userDao.findByLogin(user);
		return user;
	}
}
