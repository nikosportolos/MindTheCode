package com.mindthecode.CompanyDirectory;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*uncomment when Unit is ready*/
public class PositionMapperShould {
    private PositionMapper mapper;

    private Position positionInput;
   // private Unit unitInput;
    private PositionResponse output;

    @Before
    public void setup() {
        mapper = new PositionMapper();
        positionInput = new Position(Position.AvailablePositions.JuniorAnalyst);
        positionInput.setId(10);
     //   unitInput = new Unit("");
      //  unitInput.setId(20);
      //  positionInput.setUnit(unitInput);
        output = mapper.mapPositionToResponse(positionInput);
    }

    @Test
    public void keepSameId() {
        Assert.assertEquals(positionInput.getId(), output.getId());
    }

    @Test
    public void keepSamePositionName() {
        Assert.assertEquals(positionInput.getName().getText(), output.getName());
    }

    /*
    @Test
    public void keepSameUnit() {
        Assert.assertEquals(positionInput.getUnit(), output.getUnit());
    }*/

}
