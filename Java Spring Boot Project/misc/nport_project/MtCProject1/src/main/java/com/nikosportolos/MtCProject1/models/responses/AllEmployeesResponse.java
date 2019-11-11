package com.nikosportolos.MtCProject1.models.responses;

import com.nikosportolos.MtCProject1.models.Employee;

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
