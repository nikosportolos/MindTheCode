package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.Position;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.BusinessUnitService;
import com.mindthecode.CompanyDirectory.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PositionController {

    @Autowired
    private PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

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

    /**
     * Add
     **/

    @PostMapping("/addPosition")
    @ResponseBody
    public ResponseEntity addEmployee(@RequestBody Position newPosition) {
        try {
            System.out.println("###Adding position: " + newPosition.toString());
            var response = service.savePosition(newPosition);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while adding position #" + newPosition.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addPositions")
    @ResponseBody
    public ResponseEntity addEmployees(@RequestBody Iterable<Position> newPositions) {
        try {
            System.out.println("###Adding multiple positions");
            var response = service.savePositions(newPositions);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while adding positions"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update
     **/

    @PutMapping("/updatePosition")
    public ResponseEntity updateEmployee(@RequestBody Position position) {
        try {
            System.out.println("###Updating position: " + position.toString());
            var response = service.savePosition(position);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating position #" + position.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatePositions")
    public ResponseEntity updateEmployees(@RequestBody Iterable<Position> newPositions) {
        try {
            System.out.println("###Updating multiple positions");
            var response = service.savePositions(newPositions);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating positions"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete
     **/

    @DeleteMapping("/deletePosition")
    public ResponseEntity deleteEmployee(@RequestBody Position position) {
        try {
            System.out.println("###Deleting position: " + position.toString());
            var response = service.deletePosition(position);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting position #" + position.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletePositions")
    public ResponseEntity deleteEmployees(@RequestBody Iterable<Position> positions) {
        try {
            System.out.println("###Deleting multiple positions");
            var response = service.deletePositions(positions);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting positions"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllPositions")
    public ResponseEntity deleteAllPositions() {
        try {
            System.out.println("###Deleting all positions");
            var response = service.deleteAllPositions();

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting positions"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
