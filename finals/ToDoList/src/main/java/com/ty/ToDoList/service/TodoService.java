package com.ty.ToDoList.service;

import com.ty.ToDoList.entity.Todo;
import com.ty.ToDoList.entity.User;
import com.ty.ToDoList.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Save new todo
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    // Get todos for a specific user
    public List<Todo> getTodosByUser(User user) {
        return todoRepository.findByUser(user);
    }

    // Update completed status
    public void updateStatus(Long id, boolean completed) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if(optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setCompleted(completed);
            todoRepository.save(todo);
        }
    }

    // Delete todo
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
