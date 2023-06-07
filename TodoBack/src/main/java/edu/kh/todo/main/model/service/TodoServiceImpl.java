package edu.kh.todo.main.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.todo.main.model.dao.TodoDAO;
import edu.kh.todo.main.model.vo.Todo;

@Service
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	private TodoDAO dao;

	@Override
	public List<String> selectAll() {
		return dao.selectAll();
	}

	@Override
	public int deleteList(int todoNo) {
		return dao.deleteList(todoNo);
	}

	@Override
	public int insertList(Todo todo) {
		return dao.insertList(todo);
	}
	
	
	
	
}
