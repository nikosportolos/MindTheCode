package com.mindthecode.CompanyDirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentsMapper mapper;

    public GenericResponse<AllDepartmentsResponse> getAllDepartments() {
        return mapper.mapDepartments(repository.findAll());
    }


}
