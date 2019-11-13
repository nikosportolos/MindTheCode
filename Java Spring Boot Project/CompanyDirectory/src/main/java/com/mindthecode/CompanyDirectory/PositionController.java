package com.mindthecode.CompanyDirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionController {

    @Autowired
    private PositionService service;

    @GetMapping("/positions")
    public ResponseEntity getPositions() {
        try {
            return new ResponseEntity(service.getAllPositions(), null, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return getError();
        }
    }

    @GetMapping("/position/{id}")
    public ResponseEntity getPositionById(@PathVariable long id) {
        try {
            return new ResponseEntity(service.getPositionById(id), null, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return getError();
        }
    }

    /*
    @GetMapping("/position/{unitid}")
    public ResponseEntity getPositionsByUnitId(@PathVariable long unitid) {
        try {
            return new ResponseEntity(service.getPositionsByUnitId(unitid), null, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return getError();
        }
    }*/

    private ResponseEntity getError() {
        return new ResponseEntity(
                new ErrorResponse(0, "Error", "Something went wrong please try again"),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
