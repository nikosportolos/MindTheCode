package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
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
            return new ResponseEntity<>(service.getCompanyById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCompany")
    @ResponseBody
    public ResponseEntity addCompany(@RequestBody Company company) {
        try {
            System.out.println("###Adding company" + company.toString());
            return new ResponseEntity<>(service.saveCompany(company), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "You did not add any company"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCompanies")
    @ResponseBody
    public ResponseEntity addCompanies(@RequestBody Iterable<Company> companies) {
        try {
            System.out.println("###Adding companies");
            return new ResponseEntity<>(service.saveCompanies(companies), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "You did not add more than one companies"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCompany")
    public ResponseEntity updateCompany(@RequestBody Company company) {
        try {
            System.out.println("###Updating company : " + company.toString());
            var response = service.saveCompany(company);
            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating company #" + company.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCompanies")
    public ResponseEntity updateCompanies(@RequestBody Iterable<Company> newCompanies) {
        try {
            System.out.println("###Updating multiple companies");
            var response = service.saveCompanies(newCompanies);
            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating companies"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deleteCompany")
    public ResponseEntity deleteCompanies(@RequestBody Company company) {
        try {
            System.out.println("###Deleting  company : " + company.toString());
            var response = service.deleteCompany(company);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting company #" + company.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteCompanies")
    public ResponseEntity deleteCompanies(@RequestBody Iterable<Company> companies) {
        try {
            System.out.println("###Deleting multiple companies");
            var response = service.deleteCompanies(companies);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting companies"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllCompanies")
    public ResponseEntity deleteAllCompanies() {
        try {
            System.out.println("###Deleting all companies");
            var response = service.deleteAllCompanies();

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting companies"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
