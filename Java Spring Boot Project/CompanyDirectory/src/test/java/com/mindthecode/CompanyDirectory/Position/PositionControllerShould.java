package com.mindthecode.CompanyDirectory.Position;

import com.mindthecode.CompanyDirectory.controllers.PositionController;
import com.mindthecode.CompanyDirectory.models.entities.Position;
import com.mindthecode.CompanyDirectory.models.responses.AllPositionsResponse;
import com.mindthecode.CompanyDirectory.models.responses.PositionResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.services.PositionService;
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

public class PositionControllerShould {
    PositionController controller;

    @Mock
    PositionService service;

    @Mock
    PositionResponse Position1;

    @Mock
    PositionResponse Position2;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        List<PositionResponse> mockedPositions = new ArrayList<>();
        mockedPositions.add(Position1);
        mockedPositions.add(Position2);

        AllPositionsResponse response = new AllPositionsResponse(mockedPositions);
        GenericResponse<AllPositionsResponse> mockedResponse = new GenericResponse<>(response);

        when(service.getAllPositions()).thenReturn(mockedResponse);
        controller = new PositionController(service);
    }

    @Test
    public void returnAllPositions(){
        List<PositionResponse> list = new ArrayList<>();
        list.add(Position1);
        list.add(Position2);

        // Create the expected response
        ResponseEntity<GenericResponse<AllPositionsResponse>> expectedResponse = new ResponseEntity<>(new GenericResponse<>(new AllPositionsResponse(list)), null, HttpStatus.OK);

        // Get the actual response
        var actualResponse = controller.getPositions();

        // Check http status code
        Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

        String actual = actualResponse.getBody().toString();
        String expected = expectedResponse.getBody().toString();

        // Check response body
        Assert.assertTrue(actual.equalsIgnoreCase(expected));
    }

    @Test
    public void returnsErrorWhenServiceFails(){
        ResponseEntity expectedResponse = mockServiceFailure();
        ResponseEntity actualResponse = controller.getPositions();

        String actual = actualResponse.getBody().toString();
        String expected = expectedResponse.getBody().toString();

        // Check response body
        Assert.assertTrue(actual.equalsIgnoreCase(expected));
    }

    private ResponseEntity mockServiceFailure() {
        ErrorResponse error = new ErrorResponse(0, "Error", "Something went wrong");
        when(service.getAllPositions()).thenReturn(new GenericResponse<>(error));
        controller = new PositionController(service);
        return new ResponseEntity(new GenericResponse<>(error), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
