package com.ty.ToDoList.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;
    private String description;

    private LocalDateTime dueDate;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.ty.ToDoList.entity.User user;

    // Constructors
    public Todo() {}

    public Todo(String taskName, String description, LocalDateTime dueDate, com.ty.ToDoList.entity.User user) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.user = user;
        this.completed = false;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public com.ty.ToDoList.entity.User getUser() { return user; }
    public void setUser(com.ty.ToDoList.entity.User user) { this.user = user; }
}
