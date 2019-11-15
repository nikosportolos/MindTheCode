package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.responses.CompanyResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyMapperShould {
    private CompanyMapper companyMapper;
    private Company companyInput;
    private CompanyResponse companyResponseOutput;

    @Before
    public void setup() {
        companyMapper = new CompanyMapper();
        companyInput = new Company("InfoQuest");
        companyInput.setId(100);
        companyResponseOutput = companyMapper.mapCompanyResponseFromCompany(companyInput);
    }

    @Test
    public void keepSameId() {
        Assert.assertEquals(100, companyResponseOutput.getCompanyId());
    }

    @Test
    public void keepSameName() {
        Assert.assertEquals("InfoQuest", companyResponseOutput.getCompanyName());
    }
}


