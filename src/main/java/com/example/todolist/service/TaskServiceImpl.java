package com.example.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todolist.domain.Task;
import com.example.todolist.domain.User;
import com.example.todolist.repository.TaskRepository;
import java.io.UnsupportedEncodingException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;



import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, Task updatedTask){
            Task task = taskRepository.getOne(id);

            task.setName(updatedTask.getName());
            task.setDescription(updatedTask.getDescription());
            task.setStartDate(updatedTask.getStartDate());
            task.setEndDate(updatedTask.getEndDate());

            taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void setTaskCompleted(Long id) {
        Task task = taskRepository.getOne(id);
        task.setCompleted(true);
        taskRepository.save(task);
    }

    @Override
    public void setTaskNotCompleted(Long id) {
        Task task = taskRepository.getOne(id);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    @Override
    public List<Task> findTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    @Override
    public void assignUserToTask(Task task, User user) {
        task.setUser(user);
        taskRepository.save(task);
    }

    @Override
    public List<Task> findFreeTasks() {
        List<Task> allTasks=taskRepository.findAll();
        List<Task> freeTasks=new ArrayList<>();
        for(Task item: allTasks) {
            if(item.getUser()==null) {
                freeTasks.add(item);
            }
        }
        return freeTasks;
    }

    @Override
    public List<Task> findAllByOrderByEndDateAsc() {
        return taskRepository.findAllByOrderByEndDateAsc();
    }

    @Override
    public List<Task> findTasksByUserOrderByEndDateAsc(User user) {
        return taskRepository.findTasksByUserOrderByEndDateAsc(user);
    }
    @Autowired
    private JavaMailSender mailSender;
	public void sendVerificationEmail(String email)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = "rocks.alexis@gmail.com";
        String fromAddress = "abc@abc.com";
        String senderName = "ABC";
        String subject = "You have a task";
        String content = "Dear [[name]],<br>"
                + "You have a new task<br>"
        		+ "<a href='http://localhost:8080/profile'>Click here to view tasks</a><br><br>"
                + "Thank you,<br>"
                + "ABC";
         
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
         
        content = content.replace("[[name]]", email);
//        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
//         
//        content = content.replace("[[URL]]", verifyURL);
         
        helper.setText(content, true);
         
        mailSender.send(message);
         
    }
}
