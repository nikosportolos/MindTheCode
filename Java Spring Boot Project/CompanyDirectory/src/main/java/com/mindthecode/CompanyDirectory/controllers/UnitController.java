package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.responses.AllUnitsResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UnitController {

    @Autowired
    private UnitService service;

    @GetMapping("/units")
    @ResponseBody
    public ResponseEntity getUnits() {
        try {
            System.out.println("###Loading all units...");
            return new ResponseEntity(service.getAllUnits(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unit/{id}")
    @ResponseBody
    public ResponseEntity getUnitById(@PathVariable("id") long id) {
        try {
            System.out.println("###Loading unit by id: " + id);
            return new ResponseEntity(service.getUnitById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResponse(0, "Error", e.toString()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
