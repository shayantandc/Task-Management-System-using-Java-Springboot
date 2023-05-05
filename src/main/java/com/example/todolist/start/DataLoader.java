package com.example.todolist.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.example.todolist.domain.Role;
import com.example.todolist.domain.Task;
import com.example.todolist.domain.User;
import com.example.todolist.service.RoleService;
import com.example.todolist.service.TaskService;
import com.example.todolist.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private RoleService roleService;
    private UserService userService;
    private TaskService taskService;
    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDate startDate;
    private LocalDate endDate;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Task task;


        //ROLES --------------------------------------------------------------------------------------------------------
        Role roleAdmin = new Role("ADMIN");
        roleService.saveRole(new Role("ADMIN"));
        logger.info("saved role: " + roleAdmin.getName());

        Role roleUser = new Role("USER");
        roleService.saveRole(roleUser);
        logger.info("saved role: " + roleUser.getName());

        //USERS --------------------------------------------------------------------------------------------------------
        //1
//        User admin = new User("admin@admin.com", "admin", "adminadmin");
//        userService.createAdmin(admin);
//        logger.info("saved user: " + admin.getName());
//        
//        admin = new User("admin1@admin.com", "admin1", "adminadmin");
//        userService.createAdmin(admin);
//        logger.info("saved user: " + admin.getName());

        //2
        User user = new User("user@mail.com", "user","useruser");
        userService.createAdmin(user);
        logger.info("saved user: " + user.getName());

        //3
        user = new User("mark@mail.com", "Mark", "user");
        userService.createAdmin(user);
        logger.info("saved user: " + user.getName());

        //4
        user = new User("ann@supermail.com", "Ann", "11111111");
        userService.createAdmin(user);
        logger.info("saved user: " + user.getName());

        //5
        user = new User("ralf@bestmail.com", "Ralf", "11111111");
        userService.createAdmin(user);
        logger.info("saved user: " + user.getName());

        //6
        user = new User("kate@quickmail.com", "Kate", "11111111");
        userService.createAdmin(user);
        logger.info("saved user: " + user.getName());
        User userKate = new User();

        //7
        user = new User("tom@fakemail.com", "Tom", "11111111");
        userService.createAdmin(user);
        logger.info("saved user: " + user.getName());

        //TASKS --------------------------------------------------------------------------------------------------------
        //tasks according to Web Design Checklist https://www.beewits.com/the-ultimate-web-design-checklist-things-to-do-when-launching-a-website/#Download_a_softcopy_of_the_checklist

        //1
        /*task = new Task();
        task.setName("Collect briefing document ");
        task.setDescription("Setup first meeting with client. Collect basic data about the client. Define and collect briefing document from client.");
        startDate = LocalDate.parse("01-04-2019", formatter);
        endDate = LocalDate.parse("02-04-2019", formatter);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setCompleted(true);
        task.setUser(userService.getUserByEmail("mark@mail.com"));
        taskService.createTask(task);
        logger.info("saved task: '" + task.getName()+"' for user: "+task.getUser().getName());


        //2
        task = new Task();
        task.setName("Define project questionnaire ");
        task.setDescription("Define and send project questionnaire to the client and wait for the client’s response. Iterate on doubts you have until everybody is in agreement. Finalize project questionnaire from client.");
        startDate = LocalDate.parse("02-04-2019", formatter);
        endDate = LocalDate.parse("03-04-2019", formatter);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setCompleted(true);
        task.setUser(userService.getUserByEmail("ann@supermail.com"));
        taskService.createTask(task);
        logger.info("saved task: '" + task.getName()+"' for user: "+task.getUser().getName());

        */
    }
}

