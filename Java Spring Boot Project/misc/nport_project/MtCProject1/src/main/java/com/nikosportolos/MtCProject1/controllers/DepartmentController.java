package com.nikosportolos.MtCProject1.controllers;

import com.nikosportolos.MtCProject1.models.responses.AllDepartmentResponse;
import com.nikosportolos.MtCProject1.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService service;

    @GetMapping("/departments")
    @ResponseBody
    public AllDepartmentResponse getDepartments() {
        System.out.println("###Loading all departments...");
        return service.getAllDepartments();
    }

    @GetMapping("/department/{id}")
    @ResponseBody
    public AllDepartmentResponse getDepartmentById(@PathVariable("id") long id) {
        System.out.println("###Loading department by id: " + id);
        return service.getDepartmentById(id);
    }
}
