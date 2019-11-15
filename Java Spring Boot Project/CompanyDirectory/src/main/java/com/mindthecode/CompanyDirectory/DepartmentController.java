package com.mindthecode.CompanyDirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/allDepartments")
    public ResponseEntity getDepartments(){
        return new ResponseEntity(service.getAllDepartments());
    }

}
