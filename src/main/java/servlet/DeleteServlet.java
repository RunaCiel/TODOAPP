package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteLogic;
import model.GetTaskListLogic;
import model.ToDo;
import model.User;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		
		DeleteLogic deleteLogic = new DeleteLogic();
		boolean result = deleteLogic.execute(user, taskId);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		
		if (result == false) { // データ削除失敗時
			request.setAttribute("errorMsg", "削除に失敗しました");
		}
		
		GetTaskListLogic getTaskListLogic = new GetTaskListLogic();
		List<ToDo> toDoList =  getTaskListLogic.execute(user);
		
		if (toDoList == null) {
			request.setAttribute("errorMsg", "ToDoデータ取得に失敗しました");
		}
		
		request.setAttribute("toDoList", toDoList);
		dispatcher.forward(request, response);
		
	}

}
