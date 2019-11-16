package com.mindthecode.CompanyDirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentsMapper mapper;

    public GenericResponse<List<AllDepartmentsResponse>> getAllDepartments() {
        return (GenericResponse<List<AllDepartmentsResponse>>) mapper.mapDepartments(repository.findAll());
    }

    public  GenericResponse<List<DepartmentResponse>> getDepartmentsById(Long departmentId){
        Iterable<Department> departments = repository.findAll();
        List<DepartmentResponse> departmentsToReturn = new ArrayList<>();

        for (Department department : departments) {
            if (department.getId() == departmentId) {
                departmentsToReturn.add(mapper.mapDepartmentToDepartmentResponse(department));
            }
        }
        return  new GenericResponse<List<DepartmentResponse>>(departmentsToReturn);

    }


}
