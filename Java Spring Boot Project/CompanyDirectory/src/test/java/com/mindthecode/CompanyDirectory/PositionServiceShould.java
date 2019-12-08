package com.mindthecode.CompanyDirectory.mappers;

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

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(PositionRepository.findAll()).thenReturn(mockedPositions);

        PositionResponseFromMapper = new PositionResponse(1, "name", new Unit("Resource Management Solutions"));
        when(mapper.mapPositionToResponse(any())).thenReturn(PositionResponseFromMapper);

        service = new PositionService(mapper, PositionRepository, unitRepository);
    }

    @Test
    public void retrievePositionsFromRepository() {
        service.getAllPositions();
        Mockito.verify(PositionRepository).findAll();
    }

    @Test
    public void usesPositionMapper() {
        service.getAllPositions();
        Mockito.verify(mapper, times(2)).mapPositions(any());
    }

    @Test
    @Ignore
    public void returnsListOfPositionResponse() {
        GenericResponse<AllPositionsResponse> output = service.getAllPositions();
        Assert.assertEquals(2, output.getData().getPositions().size());
        List<PositionResponse> expectedList = new ArrayList<>();
        expectedList.add(PositionResponseFromMapper);
        expectedList.add(PositionResponseFromMapper);
        Assert.assertThat(output.getData().getPositions(), CoreMatchers.hasItems(PositionResponseFromMapper, PositionResponseFromMapper));
    }


}
