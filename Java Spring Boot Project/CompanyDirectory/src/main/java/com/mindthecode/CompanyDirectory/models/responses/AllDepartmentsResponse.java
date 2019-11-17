package com.mindthecode.CompanyDirectory.models.responses;

import java.util.List;

public class AllDepartmentsResponse {
    List<DepartmentResponse> departments;

    public AllDepartmentsResponse(List<DepartmentResponse> departments) {
        this.departments = departments;
    }

    public List<DepartmentResponse> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentResponse> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "AllDepartmentsResponse{" +
                "departments=" + departments +
                '}';
    }
}
