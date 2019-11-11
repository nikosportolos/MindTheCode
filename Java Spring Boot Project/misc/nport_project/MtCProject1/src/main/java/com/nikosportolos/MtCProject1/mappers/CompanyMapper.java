package com.nikosportolos.MtCProject1.mappers;

import com.nikosportolos.MtCProject1.models.Company;
import com.nikosportolos.MtCProject1.models.responses.CompanyResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {
    public List<CompanyResponse> mapCompanies(Iterable<Company> all) {
        List<CompanyResponse> response = new ArrayList<>();
        for (Company company : all) {
            response.add(mapCompanyToResponse(company));
        }
        return response;
    }

    public CompanyResponse mapCompanyToResponse(Company company) {
        return new CompanyResponse(company.getId(), company.getName());
    }
}
