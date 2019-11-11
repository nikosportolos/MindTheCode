package com.nikosportolos.MtCProject1.models.responses;

import com.nikosportolos.MtCProject1.models.Department;

import java.util.List;

public class AllDepartmentResponse {

    private List<DepartmentResponse> departments;

    public AllDepartmentResponse(List<DepartmentResponse> departments) {
        this.departments = departments;
    }

    public List<DepartmentResponse> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentResponse> departments) {
        this.departments = departments;
    }
}
