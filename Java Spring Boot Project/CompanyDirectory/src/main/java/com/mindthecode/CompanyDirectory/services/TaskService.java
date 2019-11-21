package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.TaskMapper;
import com.mindthecode.CompanyDirectory.models.responses.AllTasksResponse;
import com.mindthecode.CompanyDirectory.models.entities.Task;
import com.mindthecode.CompanyDirectory.models.responses.TaskResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    public GenericResponse<AllTasksResponse> getAllTasks() {
        List<TaskResponse> tasks = mapper.mapTasks(repository.findAll());
        if (tasks == null || tasks.size() > 0)
            return new GenericResponse<>(new AllTasksResponse(tasks));

        return new GenericResponse<>(new ErrorResponse(0, "Error", "No tasks were found"));
    }

    public GenericResponse<AllTasksResponse> getTaskById(long id) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        Iterable<Task> retrievedTasks = repository.findAll();

        for (Task task : retrievedTasks) {
            if (task.getId() == id)
                taskResponses.add(mapper.mapTaskToResponse(task));
        }

        if (taskResponses.size() == 0)
            return new GenericResponse<>(new ErrorResponse(0, "Unknown task", "No task found with id " + id));

        return new GenericResponse<>(new AllTasksResponse(taskResponses));
    }

    public GenericResponse<AllTasksResponse> getTasksByNumOfEmployees(long employeeNum){
        List<TaskResponse> tasks = new ArrayList<>();
        Iterable<Task> retrievedTasks = repository.findAll();

        for (Task task : retrievedTasks) {
            if (task.getEmployees().size() == employeeNum)
                tasks.add(mapper.mapTaskToResponse(task));
        }

        return new GenericResponse<>(new AllTasksResponse(tasks));
    }

    public GenericResponse<String> saveTask(Task task) {
        try {
            repository.save(task);
            return new GenericResponse<>("Saved task #" + task.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save task"));
        }
    }

    public GenericResponse<String> saveTasks(Iterable<Task> tasks) {
        try {
            repository.saveAll(tasks);
            return new GenericResponse<>("Saved tasks");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save task"));
        }
    }

    public GenericResponse<String> deleteTask(Task task) {
        try {
            repository.delete(task);
            return new GenericResponse<>("Deleted task #" + task.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete task"));
        }
    }

    public GenericResponse<String> deleteTasks(Iterable<Task> tasks) {
        try {
            repository.deleteAll(tasks);
            return new GenericResponse<>("Deleted tasks");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete task"));
        }
    }

    public GenericResponse<String> deleteAllTasks() {
        try {
            repository.deleteAll();
            return new GenericResponse<>("Deleted all tasks");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete task"));
        }
    }


}
