package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.models.responses.EmployeeResponse;

import java.util.List;

public class AllEmployeesResponse {

    private List<EmployeeResponse> employees;

    public AllEmployeesResponse(List<EmployeeResponse> employees) {
        this.employees = employees;
    }

    public List<EmployeeResponse> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponse> employees) {
        this.employees = employees;
    }
}
