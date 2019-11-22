package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class CompanyController { //this is a comment
    @Autowired
    private CompanyService service;

    @GetMapping("/companies")
    public ResponseEntity getAllCompanies() {
        try {
            System.out.println("###Loading all companies...");
            return new ResponseEntity<>(service.getAllCompanies(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity getCompanyById(@PathVariable("id") Long id) {
        try {
            System.out.println("###Loading company by id: " + id);
            return new ResponseEntity<>(service.getCompaniesById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
