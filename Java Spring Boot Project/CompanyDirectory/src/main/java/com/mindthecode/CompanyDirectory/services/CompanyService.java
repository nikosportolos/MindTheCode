package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.CompanyMapper;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.AllCompaniesResponse;
import com.mindthecode.CompanyDirectory.models.responses.CompanyResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public GenericResponse<AllCompaniesResponse> getCompanyById(Long id) {
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
            return new GenericResponse<>("Saved company #" + company.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not add company"));
        }
    }

    public GenericResponse<String> saveCompanies(Iterable<Company> companies) {
        try {
            repository.saveAll(companies);
            return new GenericResponse<>("Saved companies");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not add companies"));
        }
    }

    public GenericResponse<String> deleteCompany(Company company) {
        try {
            repository.delete(company);
            return new GenericResponse<>("Deleted company #" + company.getId());
        } catch (DataIntegrityViolationException dex) {
            dex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete company (Referential integrity constraint violation)"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete company"));
        }
    }

    public GenericResponse<String> deleteCompanies(Iterable<Company> companies) {
        try {
            repository.deleteAll(companies);
            return new GenericResponse<>("Deleted companies");
        } catch (DataIntegrityViolationException dex) {
            dex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete companies (Referential integrity constraint violation)"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete companies"));
        }
    }

    public GenericResponse<String> deleteAllCompanies() {
        try {
            repository.deleteAll();
            return new GenericResponse<>("All companies are deleted");
        } catch (DataIntegrityViolationException dex) {
            dex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete companies (Referential integrity constraint violation)"));
        } catch (Exception e) {
            e.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete all companies"));
        }
    }
}
