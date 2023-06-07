import React, { useState, useContext, useEffect } from 'react';

const TodoContext = React.createContext();

function TodoList() {
  const [todos, setTodos] = useState([]);
  const [inputValue, setInputValue] = useState('');

  useEffect(() => {
    fetch("/todo/selectAll")
        .then((res)=>{
            return res.json();
        })
        .then((data)=>{

            setTodos(data);
        });

  },[])

  const handleAddTodo = () => {
    if(inputValue == '') {
        alert("Todo 내용을 입력해주세요~!") 
    } else {
        
      fetch("/todo/insertList" , {
        method: 'post',
        headers : {               
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body :  JSON.stringify({
            todoTitle: inputValue
        })
      }).then((res)=>{
          return res.json();
      }).then((data)=>{

          if(data == 'fail insert') {
            alert("삽입 실패!")
          } else {
            setTodos(data);
          }
          
        });
        setInputValue('');

        
    }
  };

  const handleDeleteTodo = (index, todoNo) => {
    console.log(typeof todoNo)

    fetch("/todo/deleteList" , {
            method: 'post',
            headers : {               
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body :  JSON.stringify({
                todoNo: todoNo
            })
        }).then((res)=>{
            return res.json();
        }).then((data)=>{
            
            if(data == 'fail delete') {
              alert("삭제 실패!")
            } else {
              setTodos(data);
            }

        });

  };

  const handleToggleTodo = (index) => {
    const newTodos = [...todos];
    newTodos[index].isDone = !newTodos[index].isDone;

    // 미완료/완료 상태 로컬스토리지에서 기억하기
    if(newTodos[index].isDone) {
      localStorage.setItem(`${newTodos[index].todoNo}`, newTodos[index].todoNo)
    } else {
      localStorage.removeItem(`${newTodos[index].todoNo}`)
    }
    setTodos(newTodos);

  };

  return (
    <TodoContext.Provider value={{ todos, handleAddTodo, handleDeleteTodo, handleToggleTodo }}>
      <div className="todo-body">
        <div className='todo-box'>
            <h1>Todo List</h1>
            <div className='todo-input'>
                <input value={inputValue} onChange={(e) => setInputValue(e.target.value)} />
                <button className="addBtn" onClick={handleAddTodo}>Add Todo</button>
            </div>
            <ul>
              { todos.length == 0 ? 
              "등록한 Todo가 없습니다!" : 
              todos.map((todo, index) => (
                <li key={index}>
                <span style={{ textDecoration: localStorage.getItem(`${todo.todoNo}`) ? 'line-through' : 'none' }}>{todo.todoTitle}</span>
                <button className="completeBtn" onClick={() => handleToggleTodo(index)}>{localStorage.getItem(`${todo.todoNo}`) ? '미완료' : '완료'}</button>
                <button className="deleteBtn" onClick={() => handleDeleteTodo(index, todo.todoNo)}>삭제</button>
                </li>
            ))
            }
            </ul>
        </div>
      </div>
    </TodoContext.Provider>
  );
}

export default TodoList;