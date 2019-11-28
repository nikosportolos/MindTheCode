package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
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
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while adding business unit #" + newBusinessUnit.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while adding business units"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateBusinessUnit")
    public ResponseEntity updateBusinessUnit(@RequestBody BusinessUnit BusinessUnit) {
        try {
            System.out.println("###Updating businessUnit: " + BusinessUnit.toString());
            var response = service.saveBusinessUnit(BusinessUnit);
            if(response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating business unit #" + BusinessUnit.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateBusinessUnits")
    public ResponseEntity updateBusinessUnits(@RequestBody Iterable<BusinessUnit> newBusinessUnits) {
        try {
            System.out.println("###Updating multiple businessUnits");
            var response = service.saveBusinessUnits(newBusinessUnits);
            if(response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating business units"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteBusinessUnit")
    public ResponseEntity deleteBusinessUnit(@RequestBody BusinessUnit BusinessUnit) {
        try {
            System.out.println("###Deleting businessUnit: " + BusinessUnit.toString());
            var response = service.deleteBusinessUnit(BusinessUnit);
            if(response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating business unit #" + BusinessUnit.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteBusinessUnits")
    public ResponseEntity deleteBusinessUnits(@RequestBody Iterable<BusinessUnit> BusinessUnits) {
        try {
            System.out.println("###Deleting multiple Business Units");
            var response = service.deleteBusinessUnits(BusinessUnits);
            if(response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting business units" ), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllBusinessUnits")
    public ResponseEntity deleteAllBusinessUnits() {
        try {
            System.out.println("###Deleting all Business Units");
            var response = service.deleteAllBusinessUnits();
            if(response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting all business units" ), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
