package com.example.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.todolist.domain.Task;
import com.example.todolist.service.TaskService;
import com.example.todolist.service.UserService;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

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

   @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "views/usersList";
    }

    @GetMapping("user/assignTasks/{email}")
    public  String showAssignTaskForm(@PathVariable String email, Model model){

        model.addAttribute("user", userService.getUserByEmail(email));
        model.addAttribute("userTasks", taskService.findTasksByUser(userService.getUserByEmail(email)));
        model.addAttribute("freeTasks", taskService.findFreeTasks());

        return "views/assignTasksForm";
    }

    @GetMapping("user/assign/{email}/{id}")
    public String assignTask(@PathVariable String email, @PathVariable Long id, Model model) throws UnsupportedEncodingException, MessagingException {
        Task selectedTask=taskService.getTaskById(id);
        taskService.assignUserToTask(selectedTask, userService.getUserByEmail(email));
        taskService.sendVerificationEmail(email);

        return "redirect:/user/assignTasks/"+email;
    }
}
