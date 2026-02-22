package com.gevernova.TaskManager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskRequest {
    private String title;
    private String description;
    private Long userId;
    private Long categoryId;
    private Long priorityId;
}