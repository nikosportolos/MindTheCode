package com.mindthecode.CompanyDirectory.Company;

import com.mindthecode.CompanyDirectory.controllers.CompanyController;
import com.mindthecode.CompanyDirectory.models.responses.*;
import com.mindthecode.CompanyDirectory.services.CompanyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerShould
{
    private CompanyController controller;
    ResponseEntity expectedResponse;

    @Mock
    CompanyService service;

    private CompanyResponse company1;
    private CompanyResponse company2;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        company1 = new CompanyResponse(1, "InfoQuest");
        company2 = new CompanyResponse(2, "UniSystems");

        List<CompanyResponse> mockedCompanies = new ArrayList<>();
        mockedCompanies.add(company1);
        mockedCompanies.add(company2);

        AllCompaniesResponse response = new AllCompaniesResponse(mockedCompanies);
        GenericResponse<AllCompaniesResponse> mockedResponse = new GenericResponse<>(response);

        when(service.getAllCompanies()).thenReturn(mockedResponse);
        controller = new CompanyController(service);
    }

    @Test
    public void returnAllCompanies() {
        List<CompanyResponse> list = new ArrayList<>();
        list.add(company1);
        list.add(company2);

        // Create the expected response
        ResponseEntity<GenericResponse<AllCompaniesResponse>> expectedResponse = new ResponseEntity<>(new GenericResponse<>(new AllCompaniesResponse(list)), null, HttpStatus.OK);

        // Get the actual response
        var actualResponse = controller.getAllCompanies();

        // Check http status code
        Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

        String actual = actualResponse.getBody().toString();
        String expected = expectedResponse.getBody().toString();

        // Check response body
        Assert.assertTrue(actual.equalsIgnoreCase(expected));
    }
    @Test
    public void returnsErrorWhenServiceFails() {
        ResponseEntity expectedResponse = mockServiceFailure();
        ResponseEntity actualResponse = controller.getAllCompanies();

        String actual = actualResponse.getBody().toString();
        String expected = expectedResponse.getBody().toString();

        // Check response body
        Assert.assertTrue(actual.equalsIgnoreCase(expected));
    }
    private ResponseEntity mockServiceFailure() {
        ErrorResponse error = new ErrorResponse(0, "Error", "Something went wrong");
        when(service.getAllCompanies()).thenReturn(new GenericResponse<>(error));
        controller = new CompanyController(service);
        return new ResponseEntity(new GenericResponse<>(error), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
