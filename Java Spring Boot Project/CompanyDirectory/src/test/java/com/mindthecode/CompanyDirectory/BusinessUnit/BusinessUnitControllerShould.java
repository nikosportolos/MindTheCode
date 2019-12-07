package com.mindthecode.CompanyDirectory.BusinessUnit;

import com.mindthecode.CompanyDirectory.controllers.BusinessUnitController;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.AllBusinessUnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.BusinessUnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.services.BusinessUnitService;
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
public class BusinessUnitControllerShould {

    private BusinessUnitController controller;
    ResponseEntity expectedResponse;

    @Mock
    BusinessUnitService service;

    private BusinessUnitResponse bUnit1;
    private BusinessUnitResponse bUnit2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        bUnit1 = new BusinessUnitResponse(1, "Software Technical Division", new Company(1, "UniSystems"));
        bUnit2 = new BusinessUnitResponse(1, "Financial Division", new Company(1, "UniSystems"));

        List<BusinessUnitResponse> mockedBusinessUnits = new ArrayList<>();
        mockedBusinessUnits.add(bUnit1);
        mockedBusinessUnits.add(bUnit2);

        AllBusinessUnitResponse response = new AllBusinessUnitResponse(mockedBusinessUnits);
        GenericResponse<AllBusinessUnitResponse> mockedResponse = new GenericResponse<>(response);

        when(service.getAllBusinessUnits()).thenReturn(mockedResponse);
        controller = new BusinessUnitController(service);
    }

    @Test
    public void returnAllBusinessUnits() {
        List<BusinessUnitResponse> list = new ArrayList<>();
        list.add(bUnit1);
        list.add(bUnit2);

        // Create the expected response
        ResponseEntity<GenericResponse<AllBusinessUnitResponse>> expectedResponse = new ResponseEntity<>(new GenericResponse<>(new AllBusinessUnitResponse(list)), null, HttpStatus.OK);

        // Get the actual response
        var actualResponse = controller.getAllBusinessUnits();

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
        ResponseEntity actualResponse = controller.getAllBusinessUnits();

        String actual = actualResponse.getBody().toString();
        String expected = expectedResponse.getBody().toString();

        // Check response body
        Assert.assertTrue(actual.equalsIgnoreCase(expected));
    }

    private ResponseEntity mockServiceFailure() {
        ErrorResponse error = new ErrorResponse(0, "Error", "Something went wrong");
        when(service.getAllBusinessUnits()).thenReturn(new GenericResponse<>(error));
        controller = new BusinessUnitController(service);
        return new ResponseEntity(new GenericResponse<>(error), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
