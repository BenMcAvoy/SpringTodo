package com.ben.demo.todo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController 
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService toodoService) {
        this.todoService = toodoService;
    }

    @GetMapping // ("/todos")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}") // ("/todos/{id}")
    public Todo getTodoById(@PathVariable long id) {
        return todoService.getTodoById(id);
    }

    @PostMapping // ("/todos")
    public Todo createTodo(@RequestBody CreateTodoRequest request) {
        return todoService.createTodo(request.title());
    }

    @PostMapping("/{id}/complete") // ("/todos/{id}/complete?completed=true")
    public Todo setTodoCompleted(
        @PathVariable long id,
        @RequestParam(defaultValue = "true") boolean completed
    ) {
        return todoService.setTodoCompleted(id, completed);
    }

    @DeleteMapping("/{id}") // ("/todos/{id}")
    public void deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
    }

    public record CreateTodoRequest(String title) {}
}
