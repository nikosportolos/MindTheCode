package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Employee;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusinessUnitController {

    @Autowired
    private BusinessUnitService service;

    @GetMapping("/businessUnits")
    public ResponseEntity getBusinessUnits() {
        try {
            System.out.println("###Loading all business units...");
            return new ResponseEntity<>(service.getAllBusinessUnits(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/businessUnit/{id}")
    public ResponseEntity getBusinessUnitById(@PathVariable("id") long id) {
        try {
            System.out.println("###Loading business unit by id: " + id);
            return new ResponseEntity<>(service.getBusinessUnitById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBusinessUnit")
    @ResponseBody
    public ResponseEntity addBusinessUnit(@RequestBody BusinessUnit newBusinessUnit) {
        try {
            System.out.println("###Adding new businessUnit: " + newBusinessUnit.toString());
            var response = service.saveBusinessUnit(newBusinessUnit);
            if(response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBusinessUnits")
    @ResponseBody
    public ResponseEntity addBusinessUnits(@RequestBody Iterable<BusinessUnit> newBusinessUnits) {
        try {
            System.out.println("###Adding multiple new businessUnits");
            var response = service.saveBusinessUnits(newBusinessUnits);
            if(response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
