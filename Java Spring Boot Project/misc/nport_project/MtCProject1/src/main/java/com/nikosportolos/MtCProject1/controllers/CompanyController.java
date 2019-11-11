package com.nikosportolos.MtCProject1.controllers;

import com.nikosportolos.MtCProject1.models.responses.AllCompaniesResponse;
import com.nikosportolos.MtCProject1.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CompanyController {
    @Autowired
    CompanyService service;

    @GetMapping("/companies")
    @ResponseBody
    public AllCompaniesResponse getCompanies() {
        System.out.println("###Loading all companies...");
        return service.getAllCompanies();
    }

    @GetMapping("/company/{id}")
    @ResponseBody
    public AllCompaniesResponse getCompanyById(@PathVariable("id") long id) {
        System.out.println("###Loading company by id: " + id);
        return service.getCompanyById(id);
    }
}
