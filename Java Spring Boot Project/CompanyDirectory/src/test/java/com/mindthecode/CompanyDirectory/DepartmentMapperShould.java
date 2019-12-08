package com.mindthecode.CompanyDirectory;

import com.mindthecode.CompanyDirectory.mappers.DepartmentMapper;
import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.responses.DepartmentResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class DepartmentMapperShould {

    private DepartmentMapper departmentMapper;
    private Department departmentInput;
    private DepartmentResponse departmentResponseOutput;

    @Before
    public void setup() {
        departmentMapper = new DepartmentMapper();

        BusinessUnit businessUnit1 = new BusinessUnit(3, "Software Technical Division", new Company(("")));
        departmentInput = new Department(6,"Application Management", businessUnit1);
        departmentResponseOutput = new DepartmentResponse(6, "Application Management", businessUnit1);

    }

    @Test
    public void keepSameId() {
        Assert.assertEquals(departmentInput.getId(), departmentResponseOutput.getId());
    }

    @Test
    public void keepSameName() {
        Assert.assertEquals(departmentInput.getName(), departmentResponseOutput.getName());
    }
}
