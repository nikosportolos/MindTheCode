package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.common.Enums;
import com.mindthecode.CompanyDirectory.models.entities.Employee;

import java.util.List;

public class TaskResponse {
    private long id;
    private String title;
    private String description;
    private Enums.TaskDifficulty difficulty;
    private Enums.TaskStatus status;
    private List<String> updates;
    private List<Employee> employees;

    public TaskResponse() {
    }

    public TaskResponse(long id, String title, String description, Enums.TaskDifficulty difficulty, Enums.TaskStatus status, List<String> updates, List<Employee> employees) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.status = status;
        this.updates = updates;
        this.employees = employees;
    }

    public TaskResponse(long id, String title, String description, Enums.TaskDifficulty difficulty, Enums.TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.status = status;
    }

    public TaskResponse(String title, String description, Enums.TaskDifficulty difficulty, Enums.TaskStatus status) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enums.TaskDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Enums.TaskDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Enums.TaskStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.TaskStatus status) {
        this.status = status;
    }
    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
