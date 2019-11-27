package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.Department;
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



}
