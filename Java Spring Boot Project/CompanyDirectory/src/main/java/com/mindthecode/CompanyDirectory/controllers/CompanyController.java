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
public class CompanyController {
    @Autowired
    private CompanyService service;

    @GetMapping("/companies")
    public ResponseEntity getAllCompanies() {
        try {
            System.out.println("###Loading all companies...");

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Access-Control-Allow-Origin", "*");
            httpHeaders.add("Access-Control-Allow-Headers", "Origin, Content-Type, Cookie, X-CSRF-TOKEN, Accept, Authorization, X-XSRF-TOKEN, Access-Control-Allow-Origin");
            httpHeaders.add("Access-Control-Expose-Headers", "Authorization, authenticated");
            httpHeaders.add("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, OPTIONS");
            httpHeaders.add("Access-Control-Allow-Credentials", "true");

            return new ResponseEntity<>(service.getAllCompanies(), httpHeaders, HttpStatus.OK);
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
