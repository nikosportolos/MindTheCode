package com.mindthecode.CompanyDirectory.controllers;

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
            return new ResponseEntity<>(service.getAllEmployees(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee/{id}")
    @ResponseBody
    public ResponseEntity getEmployeeById(@PathVariable("id") long id) {
        try {
            System.out.println("###Loading unit by id: " + id);
            return new ResponseEntity<>(service.getEmployeeById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee/{criteria}/{id}")
    @ResponseBody
    public ResponseEntity getEmployeesByCriteria(@PathVariable("criteria") String criteria, @PathVariable("id") long id) {
        try {
            System.out.println("###Loading employee by criteria: [" + criteria + ": " + id + "]");
            return new ResponseEntity<>(service.getEmployeeByCriteria(criteria, id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add
     **/

    @PostMapping("/addEmployee")
    @ResponseBody
    public ResponseEntity addEmployee(@RequestBody Employee newEmployee) {
        try {
            System.out.println("###Adding employee: " + newEmployee.toString());
            var response = service.saveEmployee(newEmployee);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addEmployees")
    @ResponseBody
    public ResponseEntity addEmployees(@RequestBody Iterable<Employee> newEmployees) {
        try {
            System.out.println("###Adding multiple employees");
            var response = service.saveEmployees(newEmployees);

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

    @PutMapping("/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        try {
            System.out.println("###Updating employee: " + employee.toString());
            var response = service.saveEmployee(employee);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating employee #" + employee.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateEmployees")
    public ResponseEntity updateEmployees(@RequestBody Iterable<Employee> newEmployees) {
        try {
            System.out.println("###Updating multiple employees");
            var response = service.saveEmployees(newEmployees);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating employees"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete
     **/

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity deleteEmployee(@RequestBody Employee employee) {
        try {
            System.out.println("###Deleting employee: " + employee.toString());
            var response = service.deleteEmployee(employee);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting employees"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployees")
    public ResponseEntity deleteEmployees(@RequestBody Iterable<Employee> employees) {
        try {
            System.out.println("###Deleting multiple employees");
            var response = service.deleteEmployees(employees);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting employees"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllEmployees")
    public ResponseEntity deleteAllEmployees() {
        try {
            System.out.println("###Deleting all employees");
            var response = service.deleteAllEmployees();

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting employees"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
