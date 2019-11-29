package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/departments")
    public ResponseEntity getDepartments() {
        try {
            System.out.println("###Loading all departments...");
            return new ResponseEntity<>(service.getAllDepartments(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/department/{id}")
    public ResponseEntity getDepartmentById(@PathVariable("id") long id) {
        try {
            System.out.println("###Loading department by id: " + id);
            return new ResponseEntity<>(service.getDepartmentById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addDepartment")
    @ResponseBody
    public ResponseEntity addDepartment(@RequestBody Department newDepartment) {
        try {
            System.out.println("###Adding new department: " + newDepartment.toString());
            var response = service.saveDepartment(newDepartment);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addDepartments")
    @ResponseBody
    public ResponseEntity addDepartments(@RequestBody Iterable<Department> newDepartments) {
        try {
            System.out.println("###Adding new department: " + newDepartments.toString());
            var response = service.saveDepartments(newDepartments);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateDepartment")
    public ResponseEntity updateDepartment(@RequestBody Department department) {
        try {
            System.out.println("###Updating department: " + department.toString());
            var response = service.saveDepartment(department);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating department #" + department.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateDepartments")
    public ResponseEntity updateDepartments(@RequestBody Iterable<Department> newDepartments) {
        try {
            System.out.println("###Updating multiple departments");
            var response = service.saveDepartments(newDepartments);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating departments "), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteDepartment")
    public ResponseEntity deleteDepartment(@RequestBody Department department) {
        try {
            System.out.println("###Deleting department: " + department.toString());
            var response = service.deleteDepartment(department);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting department #" + department.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteDepartments")
    public ResponseEntity deleteUnits(@RequestBody Iterable<Department> departments) {
        try {
            System.out.println("###Deleting multiple units");
            var response = service.deleteDepartments(departments);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting departments"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllDepartments")
    public ResponseEntity deleteAllDepartments() {
        try {
            System.out.println("###Deleting all departments");
            var response = service.deleteAllDepartments();

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting all the departments"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
