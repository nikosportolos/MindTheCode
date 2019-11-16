package com.mindthecode.CompanyDirectory.models.responses;

public class CompanyResponse
{
    private long id;
    private String name;

    public CompanyResponse() {
    }

    public CompanyResponse(String name) {
        this.name = name;
    }

    public CompanyResponse(long id, String name) {
        this.id = id;
        this.name = name;
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
}
