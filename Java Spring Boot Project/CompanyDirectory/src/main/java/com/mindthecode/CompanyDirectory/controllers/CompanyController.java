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

    @PostMapping("/addCompanies")
    @ResponseBody
    public ResponseEntity addCompanies(@RequestBody Iterable<Company> companies)
    {
        try {
            System.out.println("Adding more than one companies");
            return new ResponseEntity(companyService.saveCompanies(companies), null, HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0,"Error","You did not add more than one companies"),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCompany")
    public ResponseEntity updateCompany(@RequestBody Company company)
    {
        try
        {
            System.out.println("###Updating company : " + company.toString());
            var response = companyService.saveCompany(company);

            if(response.getError()==null)
                return new ResponseEntity<>(response,null,HttpStatus.OK);
            else
                return  new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0,"Error","Something went wrong while updating company #" + company.getId()),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateCompanies")
    public ResponseEntity updateCompanies(@RequestBody Iterable<Company> newCompanies)
    {
        try
        {
            System.out.println("###Updating multiple companies");
            var response = companyService.saveCompanies(newCompanies);

            if(response.getError()==null)
                return new ResponseEntity<>(response,null,HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0,"Error","Something went wrong while updating companies"),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("deleteCompany")
    public ResponseEntity deleteCompanies(@RequestBody Company company)
    {
        try
        {
            System.out.println("###Deleting  company : " + company.toString());
            var response = companyService.deleteCompany(company);

            if(response.getError()==null)
                return new ResponseEntity<>(response,null,HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0,"Error","Something went wrong while deleting company #" + company.getId()),null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @DeleteMapping("/deleteCompanies")
    public ResponseEntity deleteCompanies(@RequestBody Iterable<Company> companies) {
        try {
            System.out.println("###Deleting multiple companies");
            var response = companyService.deleteCompany(companies);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting companies"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */
    @DeleteMapping("/deleteAllCompanies")
    public ResponseEntity deleteAllCompanies() {
        try {
            System.out.println("###Deleting all positions");
            var response = companyService.deleteAllCompanies();

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
