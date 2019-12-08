package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.controllers.PositionController;
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
        ResponseEntity<List<PositionResponse>> actual = controller.getPositions();
        Assert.assertThat(actual.getBody(), CoreMatchers.hasItems(Position1,Position2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFails(){
        ErrorResponse error = mockServiceFailure();

        ResponseEntity<List<PositionResponse>> actual = controller.getPositions();
        Assert.assertEquals(new GenericResponse(error), actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actual.getStatusCode());
    }

    private ErrorResponse mockServiceFailure() {
        ErrorResponse error = new ErrorResponse(0, "Error", "Something went wrong");
        when(service.getAllPositions()).thenReturn(new GenericResponse<>(error));
        controller = new PositionController(service);
        return error;
    }
}
