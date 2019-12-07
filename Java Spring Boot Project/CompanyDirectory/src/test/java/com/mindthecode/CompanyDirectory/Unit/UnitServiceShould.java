package com.mindthecode.CompanyDirectory.Unit;

import com.mindthecode.CompanyDirectory.UnitMapper;
import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.AllUnitsResponse;
import com.mindthecode.CompanyDirectory.models.responses.UnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.UnitRepository;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import com.mindthecode.CompanyDirectory.services.UnitService;
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


public class UnitServiceShould {

    private UnitService service;

    private UnitResponse unitResponseFromMapper;

    @Mock
    private UnitRepository repository;

    @Mock
    private UnitMapper mapper;

    @Mock
    private CompanyRepository companyRepository;

    private Iterable<Unit> mockedUnits = new ArrayList<Unit>() {
        {
            add(new Unit(1, "Resource Management Solutions", new Department(1, "Resource Management Solutions & S/W Production Practices", new BusinessUnit(1, "", new Company(1, "UniSystems")))));
            add(new Unit(2, "System Engineering Unit", new Department(2, "Application Management", new BusinessUnit(1, "", new Company(1, "UniSystems")))));
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(repository.findAll()).thenReturn(mockedUnits);
        unitResponseFromMapper = new UnitResponse(1, "name", new Department(1, "Resource Management Solutions & S/W Production Practices", new BusinessUnit(1, "", new Company(1, "UniSystems"))));
        when(mapper.mapUnitToResponse(any())).thenReturn(unitResponseFromMapper);
        service = new UnitService(mapper, repository);
    }

    @Test
    public void retrieveUnitsFromRepository() {
        service.getAllUnits();
        Mockito.verify(repository).findAll();
    }

    @Test
    public void usesUnitMapper() {
        service.getAllUnits();
        Mockito.verify(mapper, times(2)).mapUnitToResponse(any());
    }

    @Test
    @Ignore
    public void returnsListOfUnitResponse() {
        GenericResponse<AllUnitsResponse> output = service.getAllUnits();
        Assert.assertEquals(2, output.getData().getUnits().size());
        List<UnitResponse> expectedList = new ArrayList<>();
        expectedList.add(unitResponseFromMapper);
        expectedList.add(unitResponseFromMapper);
        Assert.assertThat(output.getData().getUnits(), CoreMatchers.hasItems(unitResponseFromMapper, unitResponseFromMapper));
    }

}
