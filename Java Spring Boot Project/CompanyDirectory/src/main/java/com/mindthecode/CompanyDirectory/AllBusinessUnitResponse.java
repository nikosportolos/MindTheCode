package com.mindthecode.CompanyDirectory;

import java.util.List;

public class AllBusinessUnitResponse {

    private List<BusinessUnitResponse> businessUnitResponses;

    public AllBusinessUnitResponse(List<BusinessUnitResponse> businessUnitResponses) {
        this.businessUnitResponses = businessUnitResponses;
    }

    public List<BusinessUnitResponse> getBusinessUnitResponses() {
        return businessUnitResponses;
    }

    public void setBusinessUnitResponses(List<BusinessUnitResponse> businessUnitResponses) {
        this.businessUnitResponses = businessUnitResponses;
    }
}
