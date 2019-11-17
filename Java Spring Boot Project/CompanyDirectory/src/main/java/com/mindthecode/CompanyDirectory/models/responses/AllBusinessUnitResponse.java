package com.mindthecode.CompanyDirectory.models.responses;

import java.util.List;

public class AllBusinessUnitResponse {

    private List<BusinessUnitResponse> businessUnits;

    public AllBusinessUnitResponse(List<BusinessUnitResponse> businessUnits) {
        this.businessUnits = businessUnits;
    }

    public List<BusinessUnitResponse> getBusinessUnits() {
        return businessUnits;
    }

    public void setBusinessUnits(List<BusinessUnitResponse> businessUnits) {
        this.businessUnits = businessUnits;
    }
}
