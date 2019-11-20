package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.models.entities.TaskStatus;

public class TaskResponse
{
    private long taskId;
    private String title;
    private String description;
    private int difficulty;
    private TaskStatus status;

    public TaskResponse() {
    }

    public TaskResponse(long taskId, String title, String description, int difficulty, TaskStatus status) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.status = status;
    }

    public TaskResponse(String title, String description, int difficulty, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.status = status;
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
