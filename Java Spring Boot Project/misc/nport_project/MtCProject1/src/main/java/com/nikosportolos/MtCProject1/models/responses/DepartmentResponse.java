package com.nikosportolos.MtCProject1.models.responses;

import com.nikosportolos.MtCProject1.models.BusinessUnit;

public class DepartmentResponse {

    /**
     * Instance variables
     **/

    private long id;
    private String name;
    private BusinessUnit businessUnit;


    /**
     * Constructors
     **/

    public DepartmentResponse() {
    }

    public DepartmentResponse(long id, String name, BusinessUnit businessUnit) {
        this.id = id;
        this.name = name;
        this.businessUnit = businessUnit;
    }

    /**
     * Getters / Setters
     **/

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
}
