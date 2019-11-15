package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.models.entities.Company;

public class BusinessUnitResponse {

    private long id;
    private String nameOfBusinessUnit;
    private Company company;

    public BusinessUnitResponse() {
    }

    public BusinessUnitResponse(long id, String nameOfBusinessUnit, Company company) {
        this.id = id;
        this.nameOfBusinessUnit = nameOfBusinessUnit;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfBusinessUnit() {
        return nameOfBusinessUnit;
    }

    public void setNameOfBusinessUnit(String nameOfBusinessUnit) {
        this.nameOfBusinessUnit = nameOfBusinessUnit;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
