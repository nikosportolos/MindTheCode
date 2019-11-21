package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.common.Enums;
import com.mindthecode.CompanyDirectory.models.entities.Task;
import com.mindthecode.CompanyDirectory.models.responses.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    public List<TaskResponse> mapTasks(Iterable<Task> all) {
        List<TaskResponse> response = new ArrayList<>();
        for (Task task : all) {
            response.add(mapTaskToResponse(task));
        }
        return response;
    }

    public TaskResponse mapTaskToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                mapTaskDifficulty(task),
                task.getTaskStatus(),
                task.getUpdates(),
                task.getEmployees()
        );
    }

    public Enums.TaskDifficulty mapTaskDifficulty(Task task) {
        int avg = (task.getEstimationA() + task.getEstimationB() + task.getEstimationC()) / 3;
        if (avg < 2) {
            return Enums.TaskDifficulty.EASY;
        } else if (avg <= 4) {
            return Enums.TaskDifficulty.MEDIUM;
        } else if (avg > 5) {
            return Enums.TaskDifficulty.HARD;
        }
        return null;
    }
}