package com.mindthecode.CompanyDirectory.Department;


import com.mindthecode.CompanyDirectory.controllers.DepartmentController;
import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.*;
import com.mindthecode.CompanyDirectory.services.DepartmentService;
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
public class DepartmentControllerShould {

    private DepartmentController controller;
    ResponseEntity expectedResponse;

    @Mock
    DepartmentService service;

    private DepartmentResponse depart1;
    private DepartmentResponse depart2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        depart1 = new DepartmentResponse(6, "Application Management", new BusinessUnit(1, "Software Technical Division", new Company(1, "UniSystems")));
        depart2 = new DepartmentResponse(7, "Credit Control Department", new BusinessUnit(1, "Financial Division", new Company(1, "UniSystems")));

        List<DepartmentResponse> mockedDepartments = new ArrayList<>();
        mockedDepartments.add(depart1);
        mockedDepartments.add(depart2);

        AllDepartmentsResponse response = new AllDepartmentsResponse(mockedDepartments);
        GenericResponse<AllDepartmentsResponse> mockedResponse = new GenericResponse<>(response);

        when(service.getAllDepartments()).thenReturn(mockedResponse);
        controller = new DepartmentController(service);

    }

    @Test
    public void returnAllDepartments() {
        List<DepartmentResponse> list = new ArrayList<>();
        list.add(depart1);
        list.add(depart2);

        // Create the expected response
        ResponseEntity<GenericResponse<AllDepartmentsResponse>> expectedResponse = new ResponseEntity<>(new GenericResponse<>(new AllDepartmentsResponse(list)), null, HttpStatus.OK);

        // Get the actual response
        var actualResponse = controller.getDepartments();

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
        ResponseEntity actualResponse = controller.getDepartments();

        String actual = actualResponse.getBody().toString();
        String expected = expectedResponse.getBody().toString();

        // Check response body
        Assert.assertTrue(actual.equalsIgnoreCase(expected));
    }

    private ResponseEntity mockServiceFailure() {
        ErrorResponse error = new ErrorResponse(0, "Error", "Something went wrong");
        when(service.getAllDepartments()).thenReturn(new GenericResponse<>(error));
        controller = new DepartmentController(service);
        return new ResponseEntity(new GenericResponse<>(error), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
