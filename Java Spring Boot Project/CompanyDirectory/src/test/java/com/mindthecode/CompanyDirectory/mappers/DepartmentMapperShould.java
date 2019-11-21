package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.responses.DepartmentResponse;
import org.junit.Before;

public class DepartmentMapperShould {

    private DepartmentMapper departmentMapper;
    private Department departmentInput;
    private DepartmentResponse departmentResponseOutput;

    @Before
    public void setup() {
        departmentMapper = new DepartmentMapper();

        BusinessUnit businessUnit1 = new BusinessUnit(3, "Software Technical Division", new Company(("")));
        departmentInput = new Department(6,"Application Management", businessUnit1);


    }
}
