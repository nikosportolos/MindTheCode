package com.mindthecode.CompanyDirectory.models.responses;

import java.util.List;

public class AllCompaniesResponse {
    private List<CompanyResponse> companyResponseList;

    public AllCompaniesResponse(List<CompanyResponse> allCompanies) {
    }

    public List<CompanyResponse> getCompanyResponseList() {
        return companyResponseList;
    }

    public void setCompanyResponseList(List<CompanyResponse> companyResponseList) {
        this.companyResponseList = companyResponseList;
    }
}
