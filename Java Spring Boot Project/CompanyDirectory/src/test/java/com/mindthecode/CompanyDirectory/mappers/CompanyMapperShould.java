package com.mindthecode.CompanyDirectory;

import com.mindthecode.CompanyDirectory.Mappers.CompanyMapper;
import com.mindthecode.CompanyDirectory.Pojo.Company;
import com.mindthecode.CompanyDirectory.Pojo.CompanyResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyMapperShould
{
    private CompanyMapper companyMapper;
    private Company companyInput;
    private CompanyResponse companyResponseOutput;

    @Before
    public void setup()
    {
        companyMapper = new CompanyMapper();
        companyInput = new Company("InfoQuest");
        companyInput.setCompanyId(100);
        companyResponseOutput = companyMapper.mapCompanyResponseFromCompany(companyInput);
    }

    @Test
    public void keepSameId()
    {
        Assert.assertEquals(100,companyResponseOutput.getCompanyId());
    }

    @Test
    public void keepSameName()
    {
        Assert.assertEquals("InfoQuest",companyResponseOutput.getCompanyName());
    }
}


