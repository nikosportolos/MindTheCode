package com.mindthecode.CompanyDirectory.Position;

import com.mindthecode.CompanyDirectory.mappers.PositionMapper;
import com.mindthecode.CompanyDirectory.models.entities.Position;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.AllPositionsResponse;
import com.mindthecode.CompanyDirectory.models.responses.PositionResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.PositionRepository;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import com.mindthecode.CompanyDirectory.repositories.UnitRepository;
import com.mindthecode.CompanyDirectory.services.PositionService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


public class PositionServiceShould {

    private PositionService service;

    PositionResponse PositionResponseFromMapper;

    @Mock
    private PositionRepository PositionRepository;

    @Mock
    private PositionMapper mapper;

    @Mock
    private UnitRepository unitRepository;

    private Iterable<Position> mockedPositions = new ArrayList<Position>() {
        {
            add(new Position(16, "Software Engineer", new Unit("Resource Management Solutions")));

            add(new Position(17, "Data Analyst", new Unit("Resource Management Solutions")));
        }
    };

    private List<PositionResponse> mockedPositionsResponse = new ArrayList<PositionResponse>() {
        {
            add(new PositionResponse(16, "Software Engineer", new Unit("Resource Management Solutions")));

            add(new PositionResponse(17, "Data Analyst", new Unit("Resource Management Solutions")));
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(PositionRepository.findAll()).thenReturn(mockedPositions);

        when(mapper.mapPositions(mockedPositions)).thenReturn(mockedPositionsResponse);

        service = new PositionService(mapper, PositionRepository);
        GenericResponse<AllPositionsResponse> mockResponse = new GenericResponse<>(new AllPositionsResponse(mockedPositionsResponse));
        System.out.println("mockResponse: " + mockResponse.toString());
        when(service.getAllPositions().getData().getPositions()).thenReturn(mockedPositionsResponse);

        PositionResponseFromMapper = new PositionResponse(1, "name", new Unit("Resource Management Solutions"));
        when(mapper.mapPositionToResponse(any())).thenReturn(PositionResponseFromMapper);

    }

    @Test
    public void retrievePositionsFromRepository() {
        service.getAllPositions();
        Mockito.verify(PositionRepository).findAll();
    }

    @Test
    public void usesPositionMapper() {
        var positions = service.getAllPositions();
        Mockito.verify(mapper, times(1)).mapPositions(any());
    }

    @Test
    public void returnsListOfPositionResponse() {
        GenericResponse<AllPositionsResponse> output = service.getAllPositions();
        System.out.println("Output: " + output.toString());

        List<PositionResponse> responses = output.getData().getPositions();
        System.out.println("Responses: " + responses.size());
        Assert.assertEquals(2, responses.size());

        Assert.assertEquals(service.getAllPositions().getData().getPositions(), mockedPositionsResponse);
    }


}
