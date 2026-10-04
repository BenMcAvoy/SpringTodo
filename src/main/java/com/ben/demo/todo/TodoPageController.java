package com.ben.demo.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
public class TodoPageController {
    private final TodoService todoService;

    public TodoPageController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String showTodos(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "todos";
    }

    @PostMapping("/ui/todos")
    public String createTodo(@RequestParam String title, RedirectAttributes redirectAttributes) {
        if (title == null || title.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Please enter a todo.");
            return "redirect:/";
        }

        todoService.createTodo(title.trim());
        return "redirect:/";
    }

    @PostMapping("/ui/todos/{id}/toggle")
    public String toggleTodo(@PathVariable long id) {
        Todo todo = todoService.getTodoById(id);
        todoService.setTodoCompleted(id, !todo.isCompleted());
        return "redirect:/"; // reload
    }

    @PostMapping("/ui/todos/{id}/delete")
    public String deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
        return "redirect:/"; // reload
    }
}
