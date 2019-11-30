package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.common.Enums;
import com.mindthecode.CompanyDirectory.models.entities.Task;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskService service;

    /**
     * Get
     **/

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTasks() {
        try {
            System.out.println("###Loading all tasks");
            return new ResponseEntity<>(service.getAllTasks(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task/{id}")
    public ResponseEntity getTaskById(@PathVariable("id") long id) {
        try {
            System.out.println("###Loading task by id: " + id);
            return new ResponseEntity<>(service.getTaskById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTasksByDiffAndNumOfEmployees/{difficulty}/{numberOfEmployees}")
    public ResponseEntity getTasksByDiffAndNumOfEmployees(@PathVariable String difficulty, @PathVariable int numberOfEmployees) {
        try {
            System.out.println("###Loading tasks...");
            return new ResponseEntity<>(service.getTasksByDiffAndNumOfEmployees(difficulty, numberOfEmployees), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTasksByEmployeeNum/{employeeNum}")
    public ResponseEntity getTasksByNumOfEmployees(@PathVariable("employeeNum") long employeeNum) {
        try {
            System.out.println("###Loading tasks by number of employees");
            return new ResponseEntity<>(service.getTasksByNumOfEmployees(employeeNum), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTasksByDifficulty/{taskDifficulty}")
    public ResponseEntity getTasksByDifficulty(@PathVariable("taskDifficulty") String taskDifficulty) {
        try {
            System.out.println("###Loading tasks by difficulty");
            return new ResponseEntity<>(service.getTasksByDifficulty(taskDifficulty), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Add
     **/

    @PostMapping("/addTask")
    @ResponseBody
    public ResponseEntity addTask(@RequestBody Task newTask) {
        try {
            System.out.println("###Adding task: " + newTask.toString());
            var response = service.saveTask(newTask);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addTasks")
    @ResponseBody
    public ResponseEntity addTasks(@RequestBody Iterable<Task> newTasks) {
        try {
            System.out.println("###Adding multiple tasks");
            var response = service.saveTasks(newTasks);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Update
     **/

    @PutMapping("/updateTask")
    public ResponseEntity updateTask(@RequestBody Task task) {
        try {
            System.out.println("###Updating task: " + task.toString());
            var response = service.saveTask(task);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating task #" + task.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateTasks")
    public ResponseEntity updateTasks(@RequestBody Iterable<Task> newTasks) {
        try {
            System.out.println("###Updating multiple tasks");
            var response = service.saveTasks(newTasks);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating tasks"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity deleteTask(@RequestBody Task task) {
        try {
            System.out.println("###Deleting task: " + task.toString());
            var response = service.deleteTask(task);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting task #" + task.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTasks")
    public ResponseEntity deleteTasks(@RequestBody Iterable<Task> tasks) {
        try {
            System.out.println("###Deleting multiple tasks");
            var response = service.deleteTasks(tasks);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting tasks"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllTasks")
    public ResponseEntity deleteAllTasks() {
        try {
            System.out.println("###Deleting all tasks");
            var response = service.deleteAllTasks();

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting all tasks"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
