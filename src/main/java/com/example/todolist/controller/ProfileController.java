package com.example.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.todolist.domain.User;
import com.example.todolist.service.TaskService;
import com.example.todolist.service.UserService;

import java.security.Principal;

@Controller
public class ProfileController {

    private UserService userService;
    private TaskService taskService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal){

        String email=principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("tasks", taskService.findTasksByUserOrderByEndDateAsc(user));
        model.addAttribute("user", user);

        return "views/profile";
    }
}
