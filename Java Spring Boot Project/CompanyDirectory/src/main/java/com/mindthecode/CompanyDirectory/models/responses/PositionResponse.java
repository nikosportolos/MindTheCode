package com.mindthecode.CompanyDirectory.models.responses;

/*uncomment when Unit is ready*/
public class PositionResponse {
    private long id;
    private String name;
  //  private Unit unit;

  public PositionResponse(long id, String name/*, Unit unit*/) {
      this.id = id;
      this.name = name;
    //  this.unit = unit;
  }

    public PositionResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    */
}
