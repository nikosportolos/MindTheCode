package com.mindthecode.CompanyDirectory.BusinessUnit;

import com.mindthecode.CompanyDirectory.BusinessUnitMapper;
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

    BusinessUnitResponse businessUnitResponseFromMapper;

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

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(businessUnitRepository.findAll()).thenReturn(mockedBusinessUnits);
        businessUnitResponseFromMapper = new BusinessUnitResponse(1, "name", new Company("Info Quest"));
        when(mapper.mapBusinessUnitToResponse(any())).thenReturn(businessUnitResponseFromMapper);
        service = new BusinessUnitService(mapper, businessUnitRepository, companyRepository);
    }

    @Test
    public void retrieveBusinessUnitsFromRepository() {
        service.getAllBusinessUnits();
        Mockito.verify(businessUnitRepository).findAll();
    }

    @Test
    public void usesBusinessUnitMapper() {
        service.getAllBusinessUnits();
        Mockito.verify(mapper, times(2)).mapBusinessUnitToResponse(any());
    }

    @Test
    @Ignore
    public void returnsListOfBusinessUnitResponse() {
        GenericResponse<AllBusinessUnitResponse> output = service.getAllBusinessUnits();
        Assert.assertEquals(2, output.getData().getBusinessUnits().size());
        List<BusinessUnitResponse> expectedList = new ArrayList<>();
        expectedList.add(businessUnitResponseFromMapper);
        expectedList.add(businessUnitResponseFromMapper);
        Assert.assertThat(output.getData().getBusinessUnits(), CoreMatchers.hasItems(businessUnitResponseFromMapper, businessUnitResponseFromMapper));
    }


}
