package com.mindthecode.CompanyDirectory.models.entities;

import com.mindthecode.CompanyDirectory.common.Enums;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private int estimationA;
    private int estimationB;
    private int estimationC;
    private Enums.TaskStatus taskStatus;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> updates;

    @ManyToMany
    private List<Employee> employees;

    public Task(String title, String description, int estimationA, int estimationB, int estimationC, Enums.TaskStatus taskStatus) {
        this.title = title;
        this.description = description;
        this.estimationA = estimationA;
        this.estimationB = estimationB;
        this.estimationC = estimationC;
        this.taskStatus = taskStatus;
    }

    public Task(String title, String description, int estimationA, int estimationB, int estimationC, Enums.TaskStatus taskStatus, List<String> updates, List<Employee> employees) {
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

    public Task(long id, String title, String description, int estimationA, int estimationB, int estimationC, Enums.TaskStatus taskStatus, List<String> updates, List<Employee> employees) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estimationA = estimationA;
        this.estimationB = estimationB;
        this.estimationC = estimationC;
        this.taskStatus = taskStatus;
        this.updates = updates;
        this.employees = employees;
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

    public Enums.TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Enums.TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
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
