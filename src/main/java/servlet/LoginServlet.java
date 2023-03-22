package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetTaskListLogic;
import model.LoginLogic;
import model.ToDo;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("userName");
		User user = new User(name);
		//user.setName(name);
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", user);
		
		if (name != null && name.length() != 0) {
			
			LoginLogic loginLogic = new LoginLogic();
			user = loginLogic.execute(user);

			GetTaskListLogic getTaskListLogic = new GetTaskListLogic();
			ArrayList<ToDo> toDoList = getTaskListLogic.execute(user);
			request.setAttribute("toDoList", toDoList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
			
		} else {
			request.setAttribute("errorMsg", "ユーザー名が入力されていません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

	}

}
