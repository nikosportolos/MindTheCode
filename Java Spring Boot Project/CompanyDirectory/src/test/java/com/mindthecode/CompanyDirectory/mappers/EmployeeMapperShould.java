package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.Employee;
import com.mindthecode.CompanyDirectory.models.responses.EmployeeResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeMapperShould {


    private EmployeeMapper employeeMapper;
    private Employee employeeInput;
    private EmployeeResponse employeeResponseOutput;

    @Before
    public void setup() {
        employeeMapper = new EmployeeMapper();
        employeeInput = new Employee();
        employeeInput.setId(100);
        employeeResponseOutput = employeeMapper.mapEmployeeToResponse(employeeInput);
    }

    @Test
    public void keepSameId() {
        Assert.assertEquals(employeeInput.getId(), employeeResponseOutput.getId());
    }

}
