package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todolist.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    Object findByName(String role_user);
}
