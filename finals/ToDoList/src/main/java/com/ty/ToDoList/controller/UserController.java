package com.ty.ToDoList.controller;

import com.ty.ToDoList.entity.User;
import com.ty.ToDoList.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Default root → redirect to signup
    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/signup";
    }

    // ✅ Signup page (GET)
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // ✅ Signup form (POST)
    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) {
        if (userService.getUserByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Username already exists!");
            return "signup";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    // ✅ Login page (GET)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // After login → redirect to user’s todos
    @GetMapping("/todos/me")
    public String redirectToTodos(@AuthenticationPrincipal UserDetails userDetails) {
        return "redirect:/todos/" + userDetails.getUsername();
    }
}
