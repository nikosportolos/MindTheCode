package com.mindthecode.CompanyDirectory.models.responses;

import java.util.ArrayList;
import java.util.List;

public class AllTasksResponse
{
    private List<TaskResponse> tasks;

    public AllTasksResponse() {
    }

    public AllTasksResponse(List<TaskResponse> tasks) {
        this.tasks = tasks;
    }

    public List<TaskResponse> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponse> tasks) {
        this.tasks = tasks;
    }
}
