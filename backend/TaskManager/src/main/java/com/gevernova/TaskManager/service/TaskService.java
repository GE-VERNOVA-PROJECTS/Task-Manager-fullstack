package com.gevernova.TaskManager.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.gevernova.TaskManager.dto.TaskRequest;
import com.gevernova.TaskManager.entity.Category;
import com.gevernova.TaskManager.entity.Priority;
import com.gevernova.TaskManager.entity.Task;
import com.gevernova.TaskManager.entity.TaskStatus;
import com.gevernova.TaskManager.entity.User;
import com.gevernova.TaskManager.repository.CategoryRepository;
import com.gevernova.TaskManager.repository.PriorityRepository;
import com.gevernova.TaskManager.repository.TaskRepository;
import com.gevernova.TaskManager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final PriorityRepository priorityRepo;

    public Task createTask(TaskRequest request) {

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Priority priority = priorityRepo.findById(request.getPriorityId())
                .orElseThrow(() -> new RuntimeException("Priority not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.PENDING);
        task.setUser(user);
        task.setCategory(category);
        task.setPriority(priority);

        return taskRepo.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task updateStatus(Long id, TaskStatus status) {
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepo.save(task);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
