package com.ty.ToDoList.controller;

import com.ty.ToDoList.entity.Todo;
import com.ty.ToDoList.entity.User;
import com.ty.ToDoList.service.TodoService;
import com.ty.ToDoList.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String getTodos(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);
        if (user == null) return "redirect:/login"; // Safety check
        model.addAttribute("username", username);
        model.addAttribute("todos", todoService.getTodosByUser(user));
        return "todos";
    }

    @PostMapping("/add")
    @ResponseBody
    public Todo addTodo(@RequestBody Todo todo, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        todo.setUser(user);
        todo.setCompleted(false);
        return todoService.save(todo);
    }

    @PostMapping("/complete/{id}")
    @ResponseBody
    public void completeTodo(@PathVariable Long id, @RequestParam boolean completed) {
        todoService.updateStatus(id, completed);
    }

    // New AJAX delete endpoint
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteTodoAjax(@PathVariable Long id) {
        todoService.delete(id);
        return "success";
    }
}
