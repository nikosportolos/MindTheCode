package com.nikosportolos.MtCProject1.services;

import com.nikosportolos.MtCProject1.mappers.CompanyMapper;
import com.nikosportolos.MtCProject1.models.Company;
import com.nikosportolos.MtCProject1.models.responses.AllCompaniesResponse;
import com.nikosportolos.MtCProject1.models.responses.CompanyResponse;
import com.nikosportolos.MtCProject1.repos.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyRepo repo;

    @Autowired
    CompanyMapper mapper;

    public AllCompaniesResponse getAllCompanies() {
        return new AllCompaniesResponse(mapper.mapCompanies(repo.findAll()));
    }

    public AllCompaniesResponse getCompanyById(long id) {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        Iterable<Company> retrievedCompanies = repo.findAll();
        for (Company company : retrievedCompanies) {
            if (company.getId() == id)
                companyResponses.add(mapper.mapCompanyToResponse(company));
        }
        return new AllCompaniesResponse(companyResponses);
    }

}
