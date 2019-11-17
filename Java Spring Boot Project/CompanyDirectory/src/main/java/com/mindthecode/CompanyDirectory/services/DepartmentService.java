package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.responses.AllDepartmentsResponse;
import com.mindthecode.CompanyDirectory.models.responses.DepartmentResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.DepartmentRepository;
import com.mindthecode.CompanyDirectory.mappers.DepartmentsMapper;
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

    public GenericResponse<AllDepartmentsResponse> getAllDepartments() {
        return new GenericResponse<>(new AllDepartmentsResponse(mapper.mapDepartments(repository.findAll())));
    }

    public GenericResponse<AllDepartmentsResponse> getDepartmentById(long id) {
        Iterable<Department> retrievedDepartments = repository.findAll();
        for (Department department : retrievedDepartments) {
            if (department.getId() == id) {
                List<DepartmentResponse> list = new ArrayList<>();
                list.add(mapper.mapDepartmentToDepartmentResponse(department));
                return new GenericResponse<>(new AllDepartmentsResponse(list));
            }
        }
        return new GenericResponse<>(new ErrorResponse(0, "Error", "Department with id " + id + " does not exist"));
    }

    public GenericResponse<String> saveDepartment(Department department) {
        try {
            repository.save(department);
            return new GenericResponse<>("company department #" + department.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save department"));
        }
    }
}
