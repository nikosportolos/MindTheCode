package com.mindthecode.CompanyDirectory.mappers;

import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.responses.DepartmentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentsMapper {

    public List<DepartmentResponse> mapDepartments(Iterable<Department> all) {
        List<DepartmentResponse> departments = new ArrayList<>();
        for (Department department : all) {
            DepartmentResponse departmentResponse = mapDepartmentToDepartmentResponse(department);
            departments.add(departmentResponse);
        }
        return departments;
    }

    public DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {
        return new DepartmentResponse(department.getId(), department.getDepartmentName(), department.getBusinessUnit());
    }
}
