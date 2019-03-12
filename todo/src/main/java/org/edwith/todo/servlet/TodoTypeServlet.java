package org.edwith.todo.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.edwith.todo.dao.TodoDao;
import org.edwith.todo.dto.TodoDto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/TodoTypeServlet/*")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoTypeServlet() {
		super();
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getPathInfo().startsWith("/")) {
			Long id = Long.parseLong(req.getPathInfo().substring(1)); 	
			
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> typeMap = mapper.readValue(inputStreamToString(req.getInputStream()),new TypeReference<Map<String, String>>(){});
			String type = typeMap.get("type");
			
			TodoDto todo = new TodoDto(id, type);
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


	
	  private static String inputStreamToString(InputStream inputStream) {
		  String result = "";
		  try(Scanner s = new Scanner(inputStream, "UTF-8")){
			  if(s.hasNext()) {
				  result =  s.useDelimiter("\\A").next();
			  }
		  }catch (Exception e){
			  e.printStackTrace();
		  }
		return result;
	  }
}
