package com.mindthecode.CompanyDirectory.models.responses;

import java.util.List;

public class AllCompaniesResponse {
    private List<CompanyResponse> companies;

    public AllCompaniesResponse(List<CompanyResponse> allCompanies) {
    }

    public List<CompanyResponse> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyResponse> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "AllCompaniesResponse{" +
                "companies=" + companies +
                '}';
    }
}
