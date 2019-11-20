package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.common.Enums;

public class TaskResponse {
    private long id;
    private String title;
    private String description;
    private int difficulty;
    private Enums.TaskStatus status;

    public TaskResponse() {
    }

    public TaskResponse(long id, String title, String description, int difficulty, Enums.TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.status = status;
    }

    public TaskResponse(String title, String description, int difficulty, Enums.TaskStatus status) {
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public Enums.TaskStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.TaskStatus status) {
        this.status = status;
    }
}
