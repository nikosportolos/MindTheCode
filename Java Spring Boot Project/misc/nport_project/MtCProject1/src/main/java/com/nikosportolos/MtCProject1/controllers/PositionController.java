package com.nikosportolos.MtCProject1.controllers;

import com.nikosportolos.MtCProject1.models.responses.AllPositionsResponse;
import com.nikosportolos.MtCProject1.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PositionController {

    @Autowired
    PositionService service;

    @GetMapping("/positions")
    @ResponseBody
    public AllPositionsResponse getPositions() {
        System.out.println("###Loading all positions...");
        return service.getAllPositions();
    }

    @GetMapping("/position/{id}")
    @ResponseBody
    public AllPositionsResponse getPositionById(@PathVariable("id") long id) {
        System.out.println("###Loading position by id: " + id);
        return service.getPositionById(id);
    }
}
