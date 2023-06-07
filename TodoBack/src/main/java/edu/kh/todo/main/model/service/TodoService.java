package edu.kh.todo.main.model.service;

import java.util.List;

import edu.kh.todo.main.model.vo.Todo;

public interface TodoService {

	/** todo 리스트 전체 조회
	 * @return todolist
	 */
	public abstract List<String> selectAll();

	/** todo 한개 삭제
	 * @param todoNo
	 * @return result
	 */
	public abstract int deleteList(int todoNo);

	/** todo 한개 삽입
	 * @param todo
	 * @return result
	 */
	public abstract int insertList(Todo todo);

}
