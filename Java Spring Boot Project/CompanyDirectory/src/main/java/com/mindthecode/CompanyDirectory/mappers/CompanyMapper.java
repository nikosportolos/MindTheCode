package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.CompanyResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {

    public List<CompanyResponse> mapCompany(Iterable<Company> all)
    {
        List<CompanyResponse> response = new ArrayList<>();
        for(Company company : all)
        {
            response.add(mapCompanyResponseFromCompany(company));
        }
        return response;
    }

    public CompanyResponse mapCompanyResponseFromCompany(Company company) {
        return new CompanyResponse(company.getId(), company.getName());
    }

    public List<CompanyResponse> mapCompanies(Iterable<Company> all) {
        List<CompanyResponse> departments = new ArrayList<>();
        for (Company company : all) {
            CompanyResponse departmentResponse = mapCompanyResponseFromCompany(company);
            departments.add(departmentResponse);
        }
        return departments;
    }
}
