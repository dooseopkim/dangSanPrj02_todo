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
		List<TodoDto> temp = dao.getTodos();
		List<TodoDto> todoList = new ArrayList<TodoDto>();
		List<TodoDto> doingList = new ArrayList<TodoDto>();
		List<TodoDto> doneList = new ArrayList<TodoDto>();
		
		for(int i=0; i < temp.size(); i++) {
			if(temp.get(i).getType().equals("TODO")) {
				todoList.add(temp.get(i));
			}else if(temp.get(i).getType().equals("DOING")) {
				doingList.add(temp.get(i));
			}else {
				doneList.add(temp.get(i));
			}
		}
		request.setAttribute("todoList", todoList);
		request.setAttribute("doingList", doingList);
		request.setAttribute("doneList", doneList);
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}
