package com.mindthecode.CompanyDirectory.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskId;
    private String title;
    private String description;
    private int estimationA;
    private int estimationB;
    private int estimationC;
    private TaskStatus taskStatus;
    private List updates;

    @ManyToMany
    private Employee employees;

    public Task(String title, String description, int estimationA, int estimationB, int estimationC, TaskStatus taskStatus) {
        this.title = title;
        this.description = description;
        this.estimationA = estimationA;
        this.estimationB = estimationB;
        this.estimationC = estimationC;
        this.taskStatus = taskStatus;
    }

    public Task(String title, String description, int estimationA, int estimationB, int estimationC, TaskStatus taskStatus, List updates, Employee employees) {
        this.title = title;
        this.description = description;
        this.estimationA = estimationA;
        this.estimationB = estimationB;
        this.estimationC = estimationC;
        this.taskStatus = taskStatus;
        this.updates = updates;
        this.employees = employees;
    }

    public Task() {
    }

    public Task(long taskId, String title, String description, int estimationA, int estimationB, int estimationC, TaskStatus taskStatus, List updates, Employee employees) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.estimationA = estimationA;
        this.estimationB = estimationB;
        this.estimationC = estimationC;
        this.taskStatus = taskStatus;
        this.updates = updates;
        this.employees = employees;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
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

    public int getEstimationA() {
        return estimationA;
    }

    public void setEstimationA(int estimationA) {
        this.estimationA = estimationA;
    }

    public int getEstimationB() {
        return estimationB;
    }

    public void setEstimationB(int estimationB) {
        this.estimationB = estimationB;
    }

    public int getEstimationC() {
        return estimationC;
    }

    public void setEstimationC(int estimationC) {
        this.estimationC = estimationC;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List getUpdates() {
        return updates;
    }

    public void setUpdates(List updates) {
        this.updates = updates;
    }

    public Employee getEmployees() {
        return employees;
    }

    public void setEmployees(Employee employees) {
        this.employees = employees;
    }
}
