package com.nikosportolos.MtCProject1.mappers;

import com.nikosportolos.MtCProject1.models.Department;
import com.nikosportolos.MtCProject1.models.responses.DepartmentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapper {
    public List<DepartmentResponse> mapDepartments(Iterable<Department> all) {
        List<DepartmentResponse> response = new ArrayList<>();
        for (Department department : all) {
            response.add(mapDepartmentToResponse(department));
        }
        return response;
    }

    public DepartmentResponse mapDepartmentToResponse(Department department) {
        return new DepartmentResponse(department.getId(), department.getName(), department.getBusinessUnit());
    }
}
