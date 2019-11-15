package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/departments")
    public ResponseEntity getDepartments(){
        return new ResponseEntity(service.getAllDepartments(), null, HttpStatus.OK);
    }

}
