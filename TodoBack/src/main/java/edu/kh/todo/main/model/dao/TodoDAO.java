package edu.kh.todo.main.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.todo.main.model.vo.Todo;

@Repository
public class TodoDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<String> selectAll() {
		return sqlSession.selectList("todoMapper.selectAll");
	}

	public int deleteList(int todoNo) {
		return sqlSession.update("todoMapper.deleteList", todoNo);
	}

	public int insertList(Todo todo) {
		return sqlSession.insert("todoMapper.insertList", todo);
	}
}
