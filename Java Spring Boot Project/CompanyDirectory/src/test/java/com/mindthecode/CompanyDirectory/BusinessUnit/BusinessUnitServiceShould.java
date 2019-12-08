package com.mindthecode.CompanyDirectory.BusinessUnit;

import com.mindthecode.CompanyDirectory.mappers.BusinessUnitMapper;
import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.AllBusinessUnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.BusinessUnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.BusinessUnitRepository;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import com.mindthecode.CompanyDirectory.services.BusinessUnitService;
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


public class BusinessUnitServiceShould {

    private BusinessUnitService service;

    private BusinessUnitResponse businessUnitResponseFromMapper;

    @Mock
    private BusinessUnitRepository businessUnitRepository;

    @Mock
    private BusinessUnitMapper mapper;

    @Mock
    private CompanyRepository companyRepository;

    private Iterable<BusinessUnit> mockedBusinessUnits = new ArrayList<BusinessUnit>() {
        {
            add(new BusinessUnit(1, "Financial Division", new Company("Unisystems")));
            add(new BusinessUnit(2, "Sales", new Company("Info Quest")));
        }
    };

    private List<BusinessUnitResponse> mockedBusinessUnitResponsess = new ArrayList<BusinessUnitResponse>() {
        {
            add(new BusinessUnitResponse(1, "Financial Division", new Company("Unisystems")));
            add(new BusinessUnitResponse(2, "Sales", new Company("Info Quest")));
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(businessUnitRepository.findAll()).thenReturn(mockedBusinessUnits);

        when(mapper.mapBusinessUnits(mockedBusinessUnits)).thenReturn(mockedBusinessUnitResponsess);

        service = new BusinessUnitService(mapper, businessUnitRepository);
        GenericResponse<AllBusinessUnitResponse> mockResponse = new GenericResponse<>(new AllBusinessUnitResponse(mockedBusinessUnitResponsess));
        System.out.println("mockResponse: " + mockResponse.toString());
        when(service.getAllBusinessUnits().getData().getBusinessUnits()).thenReturn(mockedBusinessUnitResponsess);

        businessUnitResponseFromMapper = new BusinessUnitResponse(1, "name", new Company("Info Quest"));
        when(mapper.mapBusinessUnitToResponse(any())).thenReturn(businessUnitResponseFromMapper);
    }

    @Test
    public void retrieveBusinessUnitsFromRepository() {
        service.getAllBusinessUnits();
        Mockito.verify(businessUnitRepository, times(2)).findAll();
    }

    @Test
    public void usesBusinessUnitMapper() {
        var businessUnits = service.getAllBusinessUnits();
        Mockito.verify(mapper, times(1)).mapBusinessUnits(any());
    }

    @Test
    public void returnsListOfBusinessUnitResponse() {
        GenericResponse<AllBusinessUnitResponse> output = service.getAllBusinessUnits();
        System.out.println("Output: " + output.toString());

        List<BusinessUnitResponse> responses = output.getData().getBusinessUnits();
        System.out.println("Responses: " + responses.size());
        Assert.assertEquals(2, responses.size());

        Assert.assertEquals(service.getAllBusinessUnits().getData().getBusinessUnits(), mockedBusinessUnitResponsess);
    }


}
