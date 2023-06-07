package edu.kh.todo.main.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import edu.kh.todo.main.model.service.TodoService;
import edu.kh.todo.main.model.vo.Todo;

@RestController
@RequestMapping(value = "/", produces = "application/json;charset=UTF-8")
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	/** list 전체조회
	 * @return todolist
	 */
	@GetMapping("/selectAll")
	public String selectAll() {
		List<String> todoList = service.selectAll();
		
		System.out.println(todoList);
		return new Gson().toJson(todoList);
	}
	
	@PostMapping("/deleteList")
	public String deleteList(@RequestBody HashMap<String, Object> map) {
		
		System.out.println(map);
		
		int todoNo = (int) map.get("todoNo");
		
		int result = service.deleteList(todoNo);
		String data = "";
		
		if(result == 1)  {
			return selectAll();
		} else {
			return new Gson().toJson(data);
		}
		
		
	}
	
	@PostMapping("/insertList")
	public String insertList(@RequestBody HashMap<String, Object> map) {
		
		System.out.println(map);
		
		String todoTitle = (String) map.get("todoTitle");
		
		Todo todo = new Todo(0, todoTitle, null);
		
		int result = service.insertList(todo);

		
		if(result == 1)  {
			return selectAll();
		} else {
			return new Gson().toJson("fail insert");
		}
		
	}

	
	
}
