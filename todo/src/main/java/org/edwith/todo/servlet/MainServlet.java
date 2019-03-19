package org.edwith.todo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edwith.todo.dao.TodoDao;
import org.edwith.todo.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		TodoDao dao = new TodoDao();
		List<TodoDto> todos = dao.getTodos();
		List<TodoDto> todoList = new ArrayList<TodoDto>();
		List<TodoDto> doingList = new ArrayList<TodoDto>();
		List<TodoDto> doneList = new ArrayList<TodoDto>();
		
		for(TodoDto todo : todos) {
			switch(todo.getType()) {
			case "TODO":
				todoList.add(todo);
				break;
			case "DOING":
				doingList.add(todo);
				break;
			case "DONE":
				doneList.add(todo);
				break;
			default:
				break;
			}
		}
		
		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}
