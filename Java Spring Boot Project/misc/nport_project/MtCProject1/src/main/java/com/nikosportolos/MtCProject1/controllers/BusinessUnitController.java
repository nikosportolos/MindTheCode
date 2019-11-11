package com.nikosportolos.MtCProject1.controllers;

import com.nikosportolos.MtCProject1.models.responses.AllBusinessUnitsResponse;
import com.nikosportolos.MtCProject1.repos.BusinessUnitRepo;
import com.nikosportolos.MtCProject1.models.BusinessUnit;
import com.nikosportolos.MtCProject1.services.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class BusinessUnitController {
    @Autowired
    BusinessUnitService service;

    @GetMapping("/businessUnits")
    @ResponseBody
    public AllBusinessUnitsResponse getBusinessUnits() {
        System.out.println("###Loading all business units...");
        return service.getAllBusinessUnits();
    }

    @GetMapping("/businessUnit/{id}")
    @ResponseBody
    public AllBusinessUnitsResponse getBusinessUnitById(@PathVariable("id") long id) {
        System.out.println("###Loading business unit by id: " + id);
        return service.getBusinessUnitById(id);
    }
}
