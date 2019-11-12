package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.UnitResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

public class UnitMapperShould {

    private UnitMapper unitMapper;
    private Unit unitInput;
    private UnitResponse expectedOutput;

    @Before()
    public void setup() {
        unitMapper = new UnitMapper();

        Department dummyDept = new Department();
        unitInput = new Unit(1, "Resource Management Solutions", dummyDept);
        expectedOutput = new UnitResponse(1, "Resource Management Solutions", dummyDept);
    }

    @Test
    public void keepSameId() {
        Assert.assertEquals(unitInput.getId(), expectedOutput.getId());
    }

    @Test
    public void keepSameName() {
        Assert.assertEquals(unitInput.getName(), expectedOutput.getName());
    }

    @Test
    public void keepSameDepartment() {
        Assert.assertEquals(unitInput.getDepartment(), expectedOutput.getDepartment());
    }

    @Test
    @Ignore
    public void mapUnits() {

//        List<Integer> yourList = Arrays.asList(1,2,3,4)
//        assertThat(yourList, CoreMatchers.hasItems(1,2,3,4,5));

    }

    @Test
    public void mapUnitToUnitResponse() {
        UnitResponse output = unitMapper.mapUnitToResponse(unitInput);
        Assert.assertThat(expectedOutput, Matchers.samePropertyValuesAs(output));
    }
}
