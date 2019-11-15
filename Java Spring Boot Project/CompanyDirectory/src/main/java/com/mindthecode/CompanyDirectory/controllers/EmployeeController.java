package com.nikosportolos.MtCProject1.controllers;

import com.nikosportolos.MtCProject1.common.Enums;
import com.nikosportolos.MtCProject1.models.responses.AllEmployeesResponse;
import com.nikosportolos.MtCProject1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
     * Get
     **/

    @GetMapping("/employees")
    @ResponseBody
    public AllEmployeesResponse getEmployees() {
        System.out.println("###Loading all employees...");
        return service.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public AllEmployeesResponse getEmployeeById(@PathVariable("id") long id) {
        System.out.println("###Loading employee by id: " + id);
        return service.getEmployeeById(id);
    }

    @GetMapping("/employee/{criteria}/{id}")
    @ResponseBody
    public AllEmployeesResponse getEmployeesByCriteria(@PathVariable("criteria") String criteria, @PathVariable("id") long id) {
        System.out.println("###Loading employee by criteria: [" + criteria + ": " + id + "]");
        return service.getEmployeeByCriteria(criteria, id);
    }


//    /**
//     * Add
//     **/
//
//    @PostMapping("/addEmployee")
//    public String addEmployee(@RequestBody Employee employee) {
//        System.out.println("###Adding employee: " + employee.toString());
//        repo.save(employee);
//        return "";
//    }
//
//    @PostMapping("/addEmployees")
//    public String addEmployees(@RequestBody Iterable<Employee> employees) {
//        System.out.println("###Adding multiple employees");
//        repo.saveAll(employees);
//        return "";
//    }
//
//    /**
//     * Update
//     **/
//
//    @PutMapping("/updateEmployee")
//    public String updateEmployee(@RequestBody Employee newEmployee) {
//        System.out.println("###Updating employee: " + newEmployee.toString());
//        // Get current employee
//        Employee employee = repo.findById(newEmployee.getId()).get();
//        // Change properties
//        employee.updateEmployee(newEmployee);
//        // Save updated employee in database
//        repo.save(newEmployee);
//        return "";
//    }
//
//    @PutMapping("/updateEmployeeById/{id}")
//    public String updateEmployeeById(@RequestParam Employee newEmployee, @PathVariable("id") long id) {
//        System.out.println("###Updating employee: " + newEmployee.toString());
//        // Get current employee
//        Employee employee = repo.findById(id).get();
//        // Change properties
//        employee.updateEmployee(newEmployee);
//        // Save updated employee in database
//        repo.save(newEmployee);
//        return "";
//    }
//
//    @PutMapping("/updateEmployees")
//    public String updateEmployees(@RequestBody Iterable<Employee> newEmployees) {
//        System.out.println("###Updating multiple employees");
//        for (Employee newEmployee : newEmployees) {
//            updateEmployee(newEmployee);
//        }
//        return "";
//    }
//
//    /**
//     * Delete
//     **/
//
//    @DeleteMapping("/deleteEmployee")
//    public String deleteEmployee(@RequestBody Employee employee) {
//        System.out.println("###Deleting employee: " + employee.toString());
//        repo.delete(employee);
//        return "";
//    }
//
//    @DeleteMapping("/deleteEmployeeById/{id}")
//    public String deleteEmployeeById(@PathVariable("id") long id) {
//        System.out.println("###Deleting employee by ID: " + id);
//        repo.deleteById(id);
//        return "";
//    }
//
//    @DeleteMapping("/deleteEmployees")
//    public String deleteEmployees(@RequestBody Iterable<Employee> employees) {
//        System.out.println("###Deleting multiple employees");
//        repo.deleteAll(employees);
//        return "";
//    }
//
//    @DeleteMapping("/deleteAllEmployees")
//    public String deleteAllEmployees() {
//        System.out.println("###Deleting all employees");
//        repo.deleteAll();
//        return "";
//    }
}
