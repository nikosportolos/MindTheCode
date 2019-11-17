package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;

public class DepartmentResponse {

    private long id;
    private String name;
    private BusinessUnit businessUnit;

    public DepartmentResponse(long id, String name, BusinessUnit businessUnit) {
        this.id = id;
        this.name = name;
        this.businessUnit = businessUnit;
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

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    @Override
    public String toString() {
        return "DepartmentResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", businessUnit=" + businessUnit +
                '}';
    }
}
