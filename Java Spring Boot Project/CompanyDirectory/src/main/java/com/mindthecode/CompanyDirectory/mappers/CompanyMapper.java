package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyResponse mapCompanyResponseFromCompany(Company company) {
        return new CompanyResponse(company.getId(), company.getCompanyName());
    }
}
