package todo;

import org.edwith.todo.dao.TodoDao;
import org.edwith.todo.dto.TodoDto;
import org.junit.Test;

public class TodoDaoTest {
	
	//addTodo
//	@Test
	public void addTodo() {
		TodoDto todo = new TodoDto();
		todo.setTitle("Title~~");
		todo.setSequence(2);
		todo.setType("TODO");
		todo.setName("홍길동");
		int result = new TodoDao().addTodo(todo);
		System.out.println(result + " 개의 행이 추가되었습니다.");
	}
	
	//getTodos
//	@Test
	public void getTodos() {
		for(TodoDto todo: new TodoDao().getTodos()) {
			System.out.println(todo);
		}
	}
	
	//updateTodo
	@Test
	public void updateTodo() {
		TodoDto todo = new TodoDto();
		todo.setId((long) 33);
		todo.setType("TODO");
		int result = new TodoDao().updateTodo(todo);
		System.out.println(result + " 개의 행이 업데이트 되었습니다.");
	}
}
