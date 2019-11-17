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
    CompanyRepository repository;

    public GenericResponse<AllCompaniesResponse> getAllCompanies() {
        Iterable<Company> retrievedCompanies = repository.findAll();
        List<CompanyResponse> companies = new ArrayList<>();
        for (Company company : retrievedCompanies) {
            companies.add(companyMapper.mapCompanyResponseFromCompany(company));
        }
        return new GenericResponse<>(new AllCompaniesResponse(companies));
    }

    public GenericResponse<AllCompaniesResponse> getCompaniesById(Long companyId) {
        Iterable<Company> companies = repository.findAll();
        List<CompanyResponse> companiesToReturn = new ArrayList<>();

        if (!repository.findById(companyId).isPresent()) {
            return new GenericResponse<>(new ErrorResponse(0, "Wrong Input", "Something went wrong"));
        }
        for (Company company : companies) {
            if (company.getId() == companyId) {
                companiesToReturn.add(companyMapper.mapCompanyResponseFromCompany(company));
            }
        }
        return new GenericResponse<>(new AllCompaniesResponse(companiesToReturn));
    }

    public GenericResponse<String> saveCompany(Company company) {
        try {
            repository.save(company);
            return new GenericResponse<>("company employee #" + company.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save company"));
        }
    }
}
