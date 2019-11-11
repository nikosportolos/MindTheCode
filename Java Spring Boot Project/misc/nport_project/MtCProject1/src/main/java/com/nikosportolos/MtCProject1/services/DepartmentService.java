package com.nikosportolos.MtCProject1.services;

import com.nikosportolos.MtCProject1.mappers.DepartmentMapper;
import com.nikosportolos.MtCProject1.models.Company;
import com.nikosportolos.MtCProject1.models.Department;
import com.nikosportolos.MtCProject1.models.responses.AllCompaniesResponse;
import com.nikosportolos.MtCProject1.models.responses.AllDepartmentResponse;
import com.nikosportolos.MtCProject1.models.responses.CompanyResponse;
import com.nikosportolos.MtCProject1.models.responses.DepartmentResponse;
import com.nikosportolos.MtCProject1.repos.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo repo;

    @Autowired
    DepartmentMapper mapper;

    public AllDepartmentResponse getAllDepartments() {
        return new AllDepartmentResponse(mapper.mapDepartments(repo.findAll()));
    }

    public AllDepartmentResponse getDepartmentById(long id) {

        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        Iterable<Department> retrievedDepartments = repo.findAll();
        for (Department department : retrievedDepartments) {
            if (department.getId() == id)
                departmentResponses.add(mapper.mapDepartmentToResponse(department));
        }
        return new AllDepartmentResponse(departmentResponses);
    }

}
