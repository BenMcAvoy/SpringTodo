package com.ben.demo.todo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service 
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // Create
    public Todo createTodo(String title) {
        Todo newTodo = new Todo(title, false);
        return todoRepository.save(newTodo);
    }

    // Read
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    }

    // Update
    public Todo updateTodo(long id, Todo updatedTodo) {
        Todo existingTodo = getTodoById(id);
        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setCompleted(updatedTodo.isCompleted());
        return todoRepository.save(existingTodo);
    }

    public Todo setTodoCompleted(long id, boolean completed) {
        Todo existingTodo = getTodoById(id);
        existingTodo.setCompleted(completed);
        return todoRepository.save(existingTodo);
    }

    // Delete
    public void deleteTodo(long id) {
        todoRepository.deleteById(id);
    }
}
