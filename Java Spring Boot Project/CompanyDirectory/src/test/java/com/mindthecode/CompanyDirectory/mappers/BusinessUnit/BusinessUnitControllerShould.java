package com.mindthecode.CompanyDirectory.mappers.BusinessUnit;

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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class BusinessUnitControllerShould {

    BusinessUnitController controller;

    @Mock
    BusinessUnitService service;

    @Mock
    BusinessUnitResponse bUnit1;

    @Mock
    BusinessUnitResponse bUnit2;

    @Before
    public void setup(){
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
    public void returnAllBusinessUnits(){
        ResponseEntity<List<BusinessUnitResponse>> actual = controller.getAllBusinessUnits();
        Assert.assertThat(actual.getBody(), CoreMatchers.hasItems(bUnit1,bUnit2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFails(){
        ErrorResponse error = mockServiceFailure();
        ResponseEntity<List<BusinessUnitResponse>> actual = controller.getAllBusinessUnits();
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
