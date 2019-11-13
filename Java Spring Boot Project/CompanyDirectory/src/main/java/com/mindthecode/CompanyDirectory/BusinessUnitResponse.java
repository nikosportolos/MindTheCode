package com.mindthecode.CompanyDirectory;

public class BusinessUnitResponse {

    private long id;
    private String NameOfBusinessUnit;
    //private Company company;

    public BusinessUnitResponse() {
    }

    public BusinessUnitResponse(long id, String nameOfBusinessUnit/*, Company company*/) {
        this.id = id;
        NameOfBusinessUnit = nameOfBusinessUnit;
       // this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfBusinessUnit() {
        return NameOfBusinessUnit;
    }

    public void setNameOfBusinessUnit(String nameOfBusinessUnit) {
        NameOfBusinessUnit = nameOfBusinessUnit;
    }

    /*public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }*/
}
