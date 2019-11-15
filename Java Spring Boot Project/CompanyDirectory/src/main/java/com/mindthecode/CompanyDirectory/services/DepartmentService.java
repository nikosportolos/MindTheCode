package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.models.responses.AllDepartmentsResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.DepartmentRepository;
import com.mindthecode.CompanyDirectory.mappers.DepartmentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentsMapper mapper;

    public GenericResponse<AllDepartmentsResponse> getAllDepartments() {
        return new GenericResponse<>(new AllDepartmentsResponse(mapper.mapDepartments(repository.findAll())));
    }


}
