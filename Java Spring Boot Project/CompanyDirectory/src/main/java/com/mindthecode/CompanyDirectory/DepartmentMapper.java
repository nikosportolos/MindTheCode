package com.mindthecode.CompanyDirectory;

import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.responses.DepartmentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapper {

    public List<DepartmentResponse> mapDepartments(Iterable<Department> all) {
        List<DepartmentResponse> departments = new ArrayList<>();
        for (Department department : all) {
            DepartmentResponse departmentResponse = mapDepartmentToDepartmentResponse(department);
            departments.add(departmentResponse);
        }
        return departments;
    }

    public DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {
        return new DepartmentResponse(department.getId(), department.getName(), department.getBusinessUnit());
    }
}
