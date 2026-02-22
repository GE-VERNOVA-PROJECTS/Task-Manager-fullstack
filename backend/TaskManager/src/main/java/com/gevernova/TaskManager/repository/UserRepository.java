package com.gevernova.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gevernova.TaskManager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
