package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.Position;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.PositionResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionMapperShould {
    private PositionMapper mapper;

    private Position positionInput;
    private Unit unitInput;
    private PositionResponse output;

    @Before
    public void setup() {
        mapper = new PositionMapper();
        unitInput = new Unit("Development");
        positionInput = new Position("Junior Developer",unitInput);
        positionInput.setId(10);
        unitInput.setId(20);
        positionInput.setUnit(unitInput);
        output = mapper.mapPositionToResponse(positionInput);
    }

    @Test
    public void keepSameId() {
        Assert.assertEquals(positionInput.getId(), output.getId());
    }

    @Test
    public void keepSamePositionName() {
        Assert.assertEquals("Junior Developer", output.getName());
    }

    @Test
    public void keepSameUnit() {
        Assert.assertEquals(positionInput.getUnit(), output.getUnit());
    }

}
