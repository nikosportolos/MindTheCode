package com.nikosportolos.MtCProject1.models.responses;

import com.nikosportolos.MtCProject1.models.Unit;

import java.util.List;

public class AllUnitsResponse {

    private List<UnitResponse> units;

    public AllUnitsResponse(List<UnitResponse> units) {
        this.units = units;
    }

    public List<UnitResponse> getUnits() {
        return units;
    }

    public void setUnits(List<UnitResponse> units) {
        this.units = units;
    }
}
