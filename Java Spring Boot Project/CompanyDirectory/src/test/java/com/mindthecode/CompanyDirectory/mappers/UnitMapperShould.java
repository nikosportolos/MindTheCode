package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.UnitResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class UnitMapperShould {

    private UnitMapper unitMapper;
    private Unit unitInput;
    private UnitResponse expectedOutput;

    @Before()
    public void setup() {
        unitMapper = new UnitMapper();

        expectedOutput = new UnitResponse(1, "", new Department());
    }

    @Test
    public void mapUserToUserResponse() {
        UnitResponse output = unitMapper.mapUnitToResponse(unitInput);
        Assert.assertThat(expectedOutput, Matchers.samePropertyValuesAs(output));
    }
}
