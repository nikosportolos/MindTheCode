package com.mindthecode.CompanyDirectory.models.responses;

public class CompanyResponse
{
    private long companyId;
    private String companyName;

    public CompanyResponse() {
    }

    public CompanyResponse(String companyName) {
        this.companyName = companyName;
    }

    public CompanyResponse(long companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
