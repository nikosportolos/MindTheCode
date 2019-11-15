package com.mindthecode.CompanyDirectory.Controllers;


import com.mindthecode.CompanyDirectory.models.responses.CompanyResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GetAllCompaniesResponse;
import com.mindthecode.CompanyDirectory.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/allCompanies")
    public GetAllCompaniesResponse getAllCompanies()
    {
        return new GetAllCompaniesResponse(companyService.getAllCompanies());
    }

    @GetMapping("/getCompaniesByName")
    public ResponseEntity getCompaniesById(@PathVariable Long companyId)
    {
        try{
            return new ResponseEntity(
                    new GetAllCompaniesResponse((List<CompanyResponse>) companyService.getCompaniesById(companyId)),
                            null,
                            HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();

            return new ResponseEntity(
                    new ErrorResponse(0,"Error","Something went wrong"),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
