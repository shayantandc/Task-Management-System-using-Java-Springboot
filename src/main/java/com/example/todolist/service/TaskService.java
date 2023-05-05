package com.example.todolist.service;

import com.example.todolist.domain.Task;
import com.example.todolist.domain.User;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

public interface TaskService {

    List<Task> findAll();
    void createTask(Task task);
    void updateTask(Long id, Task updatedTask);
    void deleteTask(Long id);
    Task getTaskById(Long id);

    void setTaskCompleted(Long id);
    void setTaskNotCompleted(Long id);

    List<Task> findTasksByUser(User user);
    void assignUserToTask(Task task, User user);
    List<Task>  findFreeTasks();

    List<Task> findAllByOrderByEndDateAsc();
    List<Task> findTasksByUserOrderByEndDateAsc(User user);
    public void sendVerificationEmail(String email)
            throws MessagingException, UnsupportedEncodingException;
}
