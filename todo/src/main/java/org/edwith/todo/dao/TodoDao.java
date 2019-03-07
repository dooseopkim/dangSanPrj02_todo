package org.edwith.todo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.edwith.todo.dto.TodoDto;
import org.edwith.todo.util.JDBCUtil;

public class TodoDao {
	private static final String SQL__ADD_TODO = "INSERT INTO todo(title,name,sequence,type) VALUES(?,?,?,?)";
	private static final String SQL__GET_TODOS = "SELECT id, name, regdate, sequence, title, type FROM todo ORDER BY regdate";
	private static final String SQL__UPDATE_TODO = "UPDATE todo SET type = ? WHERE id = ?";

	public int addTodo(TodoDto todo) {
		int result = 0;
		try(PreparedStatement pstmt = JDBCUtil.getConnection()
											  .prepareStatement(SQL__ADD_TODO)) {
			pstmt.setString(1, todo.getTitle());
			pstmt.setString(2, todo.getName());
			pstmt.setInt(3, todo.getSequence());
			pstmt.setString(4, todo.getType());
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<TodoDto> getTodos() {
		List<TodoDto> result = new ArrayList<TodoDto>();
		try (ResultSet rs = JDBCUtil.getConnection()
				                    .createStatement()
				                    .executeQuery(SQL__GET_TODOS)){
			while (rs.next()) {
				TodoDto todo = new TodoDto(rs.getLong("id"), rs.getString("name"), rs.getString("regdate").substring(0, 10).replace("-","."),
						rs.getInt("sequence"), rs.getString("title"), rs.getString("type"));
				result.add(todo);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}

	public int updateTodo(TodoDto todo) {
		int result = 0;
		try(PreparedStatement pstmt = JDBCUtil.getConnection()
									  		  .prepareStatement(SQL__UPDATE_TODO);) {
			pstmt.setString(1, nextType(todo.getType()));
			pstmt.setFloat(2, todo.getId());
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String nextType(String type) {
		if (type.equals("TODO")) {
			return "DOING";
		} else if (type.equals("DOING")) {
			return "DONE";
		} else {
			return type;
		}
	}
}