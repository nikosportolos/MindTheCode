package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;

public class DepartmentResponse {

    private long id;
    private String departmentName;
    private BusinessUnit businessUnit;

    public DepartmentResponse(long id, String departmentName, BusinessUnit businessUnit) {
        this.id = id;
        this.departmentName = departmentName;
        this.businessUnit = businessUnit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }
}
