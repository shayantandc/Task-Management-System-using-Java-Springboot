package com.example.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.todolist.domain.Task;
import com.example.todolist.domain.User;
import com.example.todolist.service.TaskService;
import com.example.todolist.service.UserService;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
public class TaskController {

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

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllByOrderByEndDateAsc());
        model.addAttribute("users", userService.findAll());
        return "views/tasksList";
    }

    @GetMapping("task/new")
    public String showEmptyTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "views/emptyTaskForm";
    }

    @PostMapping("task/new")
    public String createTask(@Valid Task task, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "views/emptyTaskForm";
        }
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("task/edit/{id}")
    public String showFilledTaskForm(@PathVariable Long id, Model model){
        model.addAttribute("task", taskService.getTaskById(id));
        return "views/filledTaskForm";
    }

    @PostMapping("task/edit/{id}")
    public String updateTask(@Valid Task task, BindingResult bindingResult, @PathVariable Long id, Model model) {
        if(bindingResult.hasErrors()){
            return "views/filledTaskForm";
        }
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    @GetMapping("task/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("task/markDone/{id}")
    public String setTaskCompleted(@PathVariable Long id){
        taskService.setTaskCompleted(id);
        return "redirect:/tasks";
    }

    @GetMapping("task/markUndone/{id}")
    public String setTaskNotCompleted(@PathVariable Long id){
        taskService.setTaskNotCompleted(id);
        return "redirect:/tasks";
    }

    @GetMapping("task/assignUser/{id}")
    public  String showAssignTaskForm(@PathVariable Long id, Model model) {

        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("users", userService.findAll());

        return "views/assignUserForm";
    }

    @GetMapping("task/assign/{email}/{id}")
    public String assignTask(@PathVariable String email, @PathVariable Long id, Model model){
        Task selectedTask=taskService.getTaskById(id);
        taskService.assignUserToTask(selectedTask, userService.getUserByEmail(email));
        

        return "redirect:/tasks";
    }
}


