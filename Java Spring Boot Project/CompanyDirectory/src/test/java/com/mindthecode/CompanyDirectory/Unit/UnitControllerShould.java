package com.mindthecode.CompanyDirectory.Unit;

import com.mindthecode.CompanyDirectory.controllers.UnitController;
import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.responses.AllUnitsResponse;
import com.mindthecode.CompanyDirectory.models.responses.UnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.services.UnitService;
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
public class UnitControllerShould {

    private UnitController controller;
    ResponseEntity expectedResponse;

    @Mock
    UnitService service;

    private UnitResponse unit1;
    private UnitResponse unit2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Company dummyCompany = new Company(1, "MindTheCode");
        BusinessUnit dummyBU = new BusinessUnit(1, "", dummyCompany);
        Department dummyDept = new Department(1, "Software Development Department", dummyBU);

        unit1 = new UnitResponse(1, "Software Technical Division", dummyDept);
        unit2 = new UnitResponse(1, "Financial Division", dummyDept);

        List<UnitResponse> mockedUnits = new ArrayList<>();
        mockedUnits.add(unit1);
        mockedUnits.add(unit2);

        AllUnitsResponse response = new AllUnitsResponse(mockedUnits);
        GenericResponse<AllUnitsResponse> mockedResponse = new GenericResponse<>(response);

        when(service.getAllUnits()).thenReturn(mockedResponse);
        controller = new UnitController(service);
    }

    @Test
    public void returnAllUnits() {
        List<UnitResponse> list = new ArrayList<>();
        list.add(unit1);
        list.add(unit2);

        // Create the expected response
        ResponseEntity<GenericResponse<AllUnitsResponse>> expectedResponse = new ResponseEntity<>(new GenericResponse<>(new AllUnitsResponse(list)), null, HttpStatus.OK);

        // Get the actual response
        var actualResponse = controller.getUnits();

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
        ResponseEntity actualResponse = controller.getUnits();

        String actual = actualResponse.getBody().toString();
        String expected = expectedResponse.getBody().toString();

        // Check response body
        Assert.assertTrue(actual.equalsIgnoreCase(expected));
    }

    private ResponseEntity mockServiceFailure() {
        ErrorResponse error = new ErrorResponse(0, "Error", "Something went wrong");
        when(service.getAllUnits()).thenReturn(new GenericResponse<>(error));
        controller = new UnitController(service);
        return new ResponseEntity(new GenericResponse<>(error), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
