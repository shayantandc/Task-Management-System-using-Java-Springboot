package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todolist.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
