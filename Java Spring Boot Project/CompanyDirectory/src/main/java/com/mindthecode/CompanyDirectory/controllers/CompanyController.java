package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.CompanyResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.AllCompaniesResponse;
import com.mindthecode.CompanyDirectory.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController
public class CompanyController { //this is a comment
    @Autowired
    CompanyService companyService;

    @GetMapping("/allCompanies")
    public ResponseEntity getAllCompanies() {
        try {
            return new ResponseEntity(new AllCompaniesResponse(companyService.getAllCompanies()), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCompaniesById")
    public ResponseEntity getCompaniesById(@PathVariable Long companyId) {
        try {
            return new ResponseEntity(new AllCompaniesResponse((List<CompanyResponse>) companyService.getCompaniesById(companyId)), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCompany")
    @ResponseBody
    public ResponseEntity addCompany(@RequestBody Company company)
    {
        try{
            System.out.println("Adding company" + company.toString() );
            return new ResponseEntity(companyService.saveCompany(company),null,HttpStatus.OK);

        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0,"Error","You did not add any company"),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
