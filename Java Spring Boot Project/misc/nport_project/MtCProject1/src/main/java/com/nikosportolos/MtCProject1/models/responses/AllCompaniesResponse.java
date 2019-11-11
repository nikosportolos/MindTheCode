package com.nikosportolos.MtCProject1.models.responses;

import com.nikosportolos.MtCProject1.models.Company;

import java.util.List;

public class AllCompaniesResponse {

    private List<CompanyResponse> companies;

    public AllCompaniesResponse(List<CompanyResponse> companies) {
        this.companies = companies;
    }

    public List<CompanyResponse> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyResponse> companies) {
        this.companies = companies;
    }
}
