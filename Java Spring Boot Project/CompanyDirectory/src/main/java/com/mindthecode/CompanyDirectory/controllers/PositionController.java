package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {

    @Autowired
    private PositionService service;

    @GetMapping("/positions")
    public ResponseEntity getPositions() {
        try {
            System.out.println("###Loading all positions...");
            return new ResponseEntity<>(service.getAllPositions(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/position/{id}")
    public ResponseEntity getPositionById(@PathVariable("id") long id) {
        try {
            System.out.println("###Loading position by id: " + id);
            return new ResponseEntity<>(service.getPositionById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
