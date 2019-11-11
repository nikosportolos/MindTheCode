package com.nikosportolos.MtCProject1.models.responses;

import com.nikosportolos.MtCProject1.models.BusinessUnit;
import java.util.List;

public class AllBusinessUnitsResponse {

    private List<BusinessUnitResponse> businessUnits ;

    public AllBusinessUnitsResponse(List<BusinessUnitResponse> businessUnits) {
        this.businessUnits = businessUnits;
    }

    public List<BusinessUnitResponse> getBusinessUnits() {
        return businessUnits;
    }

    public void setBusinessUnits(List<BusinessUnitResponse> businessUnits) {
        this.businessUnits = businessUnits;
    }
}
