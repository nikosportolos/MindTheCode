package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessUnitController {

    @Autowired
    private BusinessUnitService service;

    @GetMapping("/businessUnits")
    public ResponseEntity getBusinessUnits() {
        try {
            return new ResponseEntity(service.getAllBusinessUnits(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", "Something went wrong please try again"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/businessUnitById/{id}")
    public ResponseEntity getBusinessUnitById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getBusinessUnitById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", "Something went wrong please try again"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
