package com.gevernova.TaskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gevernova.TaskManager.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
