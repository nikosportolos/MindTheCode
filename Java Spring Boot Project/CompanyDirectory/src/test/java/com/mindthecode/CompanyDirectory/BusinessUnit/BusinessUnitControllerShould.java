package com.mindthecode.CompanyDirectory.BusinessUnit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindthecode.CompanyDirectory.controllers.BusinessUnitController;
import com.mindthecode.CompanyDirectory.models.responses.AllBusinessUnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.BusinessUnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.services.BusinessUnitService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

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
        ResponseEntity expectedResponse = new ResponseEntity<>(new GenericResponse<>(new AllBusinessUnitResponse(list)), null, HttpStatus.OK);

        // Get the actual response
        ResponseEntity actualResponse = controller.getAllBusinessUnits();

        Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void returnsErrorWhenServiceFails() {
        ErrorResponse error = mockServiceFailure();
        ResponseEntity actual = controller.getAllBusinessUnits();

        Assert.assertEquals(error, actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode());
    }

    private ErrorResponse mockServiceFailure() {
        ErrorResponse error = new ErrorResponse(0, "Error", "Something went wrong");
        when(service.getAllBusinessUnits()).thenReturn(new GenericResponse<>(error));
        controller = new BusinessUnitController(service);
        return error;
    }
}
