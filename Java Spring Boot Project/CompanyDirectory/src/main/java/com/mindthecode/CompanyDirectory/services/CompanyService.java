package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.CompanyMapper;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.*;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper mapper;

    @Autowired
    private CompanyRepository repository;

    public GenericResponse<AllCompaniesResponse> getAllCompanies() {
        List<CompanyResponse> companies = mapper.mapCompanies(repository.findAll());
        System.out.println("Loaded " + companies.size() + " companies");
        if (companies.size() > 0)
            return new GenericResponse<>(new AllCompaniesResponse(companies));

        return new GenericResponse<>(new ErrorResponse(0, "Error", "No companies were found"));
    }

    public GenericResponse<AllCompaniesResponse> getCompaniesById(Long id) {
        Iterable<Company> companies = repository.findAll();
        List<CompanyResponse> companiesToReturn = new ArrayList<>();

        for (Company company : companies) {
            if (company.getId() == id) {
                companiesToReturn.add(mapper.mapCompanyResponseFromCompany(company));
            }
        }

        if (companiesToReturn.size() == 0)
            return new GenericResponse<>(new ErrorResponse(0, "Unknown company", "No company found with id " + id));

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
