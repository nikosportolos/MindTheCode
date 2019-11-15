package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.UnitResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnitMapperShould {

    private UnitMapper unitMapper;
    private Unit unitInput;
    private UnitResponse expectedOutput;

    @Before()
    public void setup() {
        unitMapper = new UnitMapper();

        Department dummyDept = new Department(1, "Software Development Department", new BusinessUnit(("")));
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
    public void mapUnits() {
        List<Unit> unitList = Arrays.asList(unitInput, new Unit(2, "International", new Department(1, "Software Development Department", new BusinessUnit(("")))));

        List<UnitResponse> outputList = new ArrayList<>();
        outputList.add(expectedOutput);
        outputList.add(new UnitResponse(2, "International", new Department(1, "Software Development Department", new BusinessUnit(("")))));

        List<UnitResponse> expectedList = unitMapper.mapUnits(unitList);
        Assert.assertThat(expectedList, Matchers.samePropertyValuesAs(outputList));
    }

    @Test
    public void mapUnitToUnitResponse() {
        UnitResponse output = unitMapper.mapUnitToResponse(unitInput);
        Assert.assertThat(expectedOutput, Matchers.samePropertyValuesAs(output));
    }
}
