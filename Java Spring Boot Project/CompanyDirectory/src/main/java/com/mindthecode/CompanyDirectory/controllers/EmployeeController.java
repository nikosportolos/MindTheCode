package com.mindthecode.CompanyDirectory.controllers;

<<<<<<< HEAD
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController
{
=======
import com.mindthecode.CompanyDirectory.models.entities.Employee;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getEmployees() {
        try {
            System.out.println("###Loading all employees...");
            return new ResponseEntity(service.getAllEmployees(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", e.toString()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity getEmployeeById(@PathVariable("id") long id) {
        try {
            System.out.println("###Loading unit by id: " + id);
            return new ResponseEntity(service.getEmployeeById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", e.toString()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee/{criteria}/{id}")
    @ResponseBody
    public ResponseEntity getEmployeesByCriteria(@PathVariable("criteria") String criteria, @PathVariable("id") long id) {
        try {
            System.out.println("###Loading employee by criteria: [" + criteria + ": " + id + "]");
            return new ResponseEntity(service.getEmployeeByCriteria(criteria, id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", e.toString()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add
     **/

    @PostMapping("/addEmployee")
    @ResponseBody
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        try {
            System.out.println("###Adding employee: " + employee.toString());
            return new ResponseEntity(service.saveEmployee(employee), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", e.toString()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addEmployees")
    @ResponseBody
    public ResponseEntity addEmployees(@RequestBody Iterable<Employee> employees) {
        try {
            System.out.println("###Adding multiple employees");
            return new ResponseEntity(service.saveEmployees(employees), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", e.toString()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
>>>>>>> 8e27805ded00c4357629bb52a354c69458047106
}
