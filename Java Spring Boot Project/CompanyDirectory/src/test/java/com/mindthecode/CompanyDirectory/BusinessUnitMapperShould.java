package com.mindthecode.CompanyDirectory;

import com.mindthecode.CompanyDirectory.mappers.BusinessUnitMapper;
import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.responses.BusinessUnitResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusinessUnitMapperShould {

    private BusinessUnitMapper mapper;
    private BusinessUnit businessUnitInput;
    private BusinessUnitResponse expectedOutput;

    @Before
    public void setup(){
        mapper = new BusinessUnitMapper();
        businessUnitInput = new BusinessUnit("Services");
        businessUnitInput.setId(1);
        expectedOutput = new BusinessUnitResponse(1,"Services");
    }

    @Test
    public void KeepSameId(){
        Assert.assertEquals(businessUnitInput.getId(), expectedOutput.getId());
    }

    @Test
    public void KeepSameNameOfBusinessUnit(){
        Assert.assertEquals(businessUnitInput.getNameOfBusinessUnit(), expectedOutput.getNameOfBusinessUnit());
    }

    @Test
    public void mapBusinessinessUnitToResponse(){
        BusinessUnitResponse output = mapper.mapBusinessinessUnitToResponse(businessUnitInput);
        Assert.assertThat(expectedOutput, Matchers.samePropertyValuesAs(output));
    }
}
