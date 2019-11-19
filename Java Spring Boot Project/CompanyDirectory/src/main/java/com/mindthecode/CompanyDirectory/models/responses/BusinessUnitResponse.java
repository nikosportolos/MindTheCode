package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.models.entities.Company;

public class BusinessUnitResponse {

    private long id;
    private String name;
    private Company company;

    public BusinessUnitResponse() {
    }

    public BusinessUnitResponse(long id, String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "BusinessUnitResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                '}';
    }
}
