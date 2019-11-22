package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.CompanyMapper;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.AllCompaniesResponse;
import com.mindthecode.CompanyDirectory.models.responses.CompanyResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    private CompanyMapper companyMapper = new CompanyMapper();

    @Autowired
    CompanyRepository companyRepository;

    public List<CompanyResponse> getAllCompanies() {
        Iterable<Company> retrievedCompanies = companyRepository.findAll();
        List<CompanyResponse> companies = new ArrayList<>();
        for (Company company : retrievedCompanies) {
            companies.add(companyMapper.mapCompanyResponseFromCompany(company));
        }
        return companies;
    }

    public GenericResponse<List<CompanyResponse>> getCompaniesById(Long companyId) {
        Iterable<Company> companies = companyRepository.findAll();
        List<CompanyResponse> companiesToReturn = new ArrayList<>();

        if (!companyRepository.findById(companyId).isPresent()) {
            return new GenericResponse<>(new ErrorResponse(0, "Wrong Input", "Something went wrong"));
        }
        for (Company company : companies) {
            if (company.getId() == companyId) {
                companiesToReturn.add(companyMapper.mapCompanyResponseFromCompany(company));
            }
        }
        return new GenericResponse<>(companiesToReturn);
    }

    public GenericResponse<AllCompaniesResponse> saveCompany (Company company)
    {
        companyRepository.save(company);
        List<CompanyResponse> retrievedCompany = new ArrayList<>();
        retrievedCompany.add(companyMapper.mapCompanyResponseFromCompany(company));
        return new GenericResponse<>(new AllCompaniesResponse(retrievedCompany));
    }

   /* public GenericResponse<AllCompaniesResponse> saveCompanies(Iterable<Company> companies)
    {
        companyRepository.saveAll(companies);
        return new GenericResponse<>(new AllCompaniesResponse(companyMapper.mapCompanyResponseFromCompany(companies)));
    } */
}
