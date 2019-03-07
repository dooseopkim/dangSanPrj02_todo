package org.edwith.todo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edwith.todo.dao.TodoDao;
import org.edwith.todo.dto.TodoDto;

@WebServlet("/TodoTypeServlet")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String type = req.getParameter("type");
		TodoDto todo = new TodoDto(id,type);
		TodoDao dao = new TodoDao();
		resp.setContentType("text/html;charset=utf-8");
		if(dao.updateTodo(todo) == 1) {
			System.out.println("success update todo");
			resp.getWriter().write("success");
		}else {
			System.out.println("fail update todo");
			resp.getWriter().write("fail");
		}
	}
	

}
