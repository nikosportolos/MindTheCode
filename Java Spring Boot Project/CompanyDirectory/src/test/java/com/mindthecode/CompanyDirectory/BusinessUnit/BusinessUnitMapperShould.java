package com.mindthecode.CompanyDirectory.BusinessUnit;

import com.mindthecode.CompanyDirectory.mappers.BusinessUnitMapper;
import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.BusinessUnitResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BusinessUnitMapperShould {

    private BusinessUnitMapper mapper;
    private BusinessUnit businessUnitInput;
    private Company companyInput;
    private BusinessUnitResponse expectedOutput;

    @Before
    public void setup(){
        mapper = new BusinessUnitMapper();
        businessUnitInput = new BusinessUnit(1,"Financial Division",companyInput);
        businessUnitInput.setId(10);
        companyInput = new Company("");
        companyInput.setId(12);
        businessUnitInput.setCompany(companyInput);
        expectedOutput = mapper.mapBusinessUnitToResponse(businessUnitInput);
    }

    @Test
    public void KeepSameId(){
        Assert.assertEquals(businessUnitInput.getId(), expectedOutput.getId());
    }

    @Test
    public void KeepSameNameOfBusinessUnit(){
        Assert.assertEquals(businessUnitInput.getName(), expectedOutput.getName());
    }

    @Test
    public void KeepSameCompanyName(){
        Assert.assertEquals(businessUnitInput.getCompany(), expectedOutput.getCompany());
    }

}
