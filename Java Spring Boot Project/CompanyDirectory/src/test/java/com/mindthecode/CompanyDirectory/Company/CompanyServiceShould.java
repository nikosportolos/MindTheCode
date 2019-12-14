package com.mindthecode.CompanyDirectory.Company;

import com.mindthecode.CompanyDirectory.mappers.CompanyMapper;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.AllCompaniesResponse;
import com.mindthecode.CompanyDirectory.models.responses.CompanyResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import com.mindthecode.CompanyDirectory.services.CompanyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CompanyServiceShould
{
    private CompanyService service;

    private CompanyResponse companyResponseFromMapper;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    private Iterable<Company> mockedCompanies = new ArrayList<Company>() {
        {
            add(new Company(1,"Unisystems"));
            add(new Company(2,"Info Quest"));
        }
    };

    private List<CompanyResponse> mockedCompaniesResponses = new ArrayList<CompanyResponse>() {
        {
            add(new CompanyResponse(1,"Unisystems"));
            add(new CompanyResponse(2,"Info Quest"));
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(companyRepository.findAll()).thenReturn(mockedCompanies);

        when(companyMapper.mapCompanies(mockedCompanies)).thenReturn(mockedCompaniesResponses);

        service = new CompanyService(companyMapper, companyRepository);
        GenericResponse<AllCompaniesResponse> mockResponse = new GenericResponse<>(new AllCompaniesResponse(mockedCompaniesResponses));
        System.out.println("mockResponse: " + mockResponse.toString());
        when(service.getAllCompanies().getData().getCompanies()).thenReturn(mockedCompaniesResponses);

        companyResponseFromMapper = new CompanyResponse(1,"Info Quest");
        when(companyMapper.mapCompanyResponseFromCompany(any())).thenReturn(companyResponseFromMapper);
    }

    @Test
    public void retrieveCompaniesFromRepository() {
        service.getAllCompanies();
        Mockito.verify(companyRepository, times(2)).findAll();
    }

    @Test
    public void usesCompanyMapper() {
        var companies = service.getAllCompanies();
        Mockito.verify(companyMapper, times(1)).mapCompanies(any());
    }

    @Test
    public void returnsListOfCompanyResponse() {
        GenericResponse<AllCompaniesResponse> output = service.getAllCompanies();
        System.out.println("Output: " + output.toString());

        List<CompanyResponse> responses = output.getData().getCompanies();
        System.out.println("Responses: " + responses.size());
        Assert.assertEquals(2, responses.size());

        Assert.assertEquals(service.getAllCompanies().getData().getCompanies(), mockedCompaniesResponses);
    }


}
