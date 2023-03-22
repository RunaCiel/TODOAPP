package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetTaskListLogic;
import model.PostTaskLogic;
import model.ToDo;
import model.User;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		
		GetTaskListLogic getTaskListLogic = new GetTaskListLogic();
		List<ToDo> toDoList = getTaskListLogic.execute(user);
		request.setAttribute("toDoList", toDoList);
		
		if (user == null) {
			response.sendRedirect("/index.jsp");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String task = request.getParameter("task");
		String dateString = request.getParameter("taskDate");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		
		if (task != null && task.length() != 0 && dateString != null && dateString.length() != 0) {
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = dateFormat.parse(dateString);

				ToDo toDo = new ToDo();
				toDo.setTask(task);
				toDo.setDate(date);
				toDo.setId(user.getId());
				
				PostTaskLogic postTaskLogic = new PostTaskLogic();
				postTaskLogic.execute(toDo);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		} else if (task.length() == 0 && dateString.length() == 0) {
			request.setAttribute("errorMsg", "内容と期日が入力されていません");
		} else if (task != null && task.length() != 0 && dateString == null || dateString.length() == 0) {
			request.setAttribute("errorMsg", "期日が入力されていません");
		} else if (task == null || task.length() == 0 && dateString != null && dateString.length() != 0) {
			request.setAttribute("errorMsg", "内容が入力されていません");
		}

		GetTaskListLogic getTaskListLogic = new GetTaskListLogic();
		List<ToDo> toDoList = getTaskListLogic.execute(user);
		request.setAttribute("toDoList", toDoList);
		
		if (user == null) {
			response.sendRedirect("/index.jsp");
		} else {
			dispatcher.forward(request, response);
		}

	}

}
