package com.mindthecode.CompanyDirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/allDepartments")
    public ResponseEntity getDepartments(){
        return new ResponseEntity(
                new AllDepartmentsResponse((List<DepartmentResponse>) service.getAllDepartments()),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping("/departmentsById/{departmentId}")
    public ResponseEntity getDepartmentsById(@PathVariable Long departmentId){
        return new ResponseEntity(
                new AllDepartmentsResponse((List<DepartmentResponse>) service.getDepartmentsById(departmentId)),
                null,
                HttpStatus.OK
        );
    }

}
