package com.gevernova.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gevernova.TaskManager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {}
