package com.mindthecode.CompanyDirectory.controllers;

import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UnitController {

    @Autowired
    private UnitService service;

    public UnitController(UnitService service) {
        this.service = service;
    }

    @GetMapping("/units")
    @ResponseBody
    public ResponseEntity getUnits() {
        try {
            System.out.println("###Loading all units...");
            return new ResponseEntity<>(service.getAllUnits(), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unit/{id}")
    @ResponseBody
    public ResponseEntity getUnitById(@PathVariable("id") long id) {
        try {
            System.out.println("###Loading unit by id: " + id);
            return new ResponseEntity<>(service.getUnitById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Add
     **/

    @PostMapping("/addUnit")
    @ResponseBody
    public ResponseEntity addUnit(@RequestBody Unit newUnit) {
        try {
            System.out.println("###Adding unit: " + newUnit.toString());
            var response = service.saveUnit(newUnit);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addUnits")
    @ResponseBody
    public ResponseEntity addUnits(@RequestBody Iterable<Unit> newUnits) {
        try {
            System.out.println("###Adding multiple units");
            var response = service.saveUnits(newUnits);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update
     **/

    @PutMapping("/updateUnit")
    public ResponseEntity updateUnit(@RequestBody Unit unit) {
        try {
            System.out.println("###Updating unit: " + unit.toString());
            var response = service.saveUnit(unit);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating unit #" + unit.getId()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateUnits")
    public ResponseEntity updateUnits(@RequestBody Iterable<Unit> newUnits) {
        try {
            System.out.println("###Updating multiple units");
            var response = service.saveUnits(newUnits);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while updating units"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete
     **/

    @DeleteMapping("/deleteUnit")
    public ResponseEntity deleteUnit(@RequestBody Unit unit) {
        try {
            System.out.println("###Deleting unit: " + unit.toString());
            var response = service.deleteUnit(unit);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting units"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUnits")
    public ResponseEntity deleteUnits(@RequestBody Iterable<Unit> units) {
        try {
            System.out.println("###Deleting multiple units");
            var response = service.deleteUnits(units);

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting units"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAllUnits")
    public ResponseEntity deleteAllUnits() {
        try {
            System.out.println("###Deleting all units");
            var response = service.deleteAllUnits();

            if (response.getError() == null)
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            else
                return new ResponseEntity<>(response.getError(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(0, "Error", "Something went wrong while deleting units"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
