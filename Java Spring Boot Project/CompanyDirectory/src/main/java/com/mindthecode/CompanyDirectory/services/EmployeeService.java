package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.EmployeeMapper;
import com.mindthecode.CompanyDirectory.models.responses.AllEmployeesResponse;
import com.mindthecode.CompanyDirectory.models.entities.Employee;
import com.mindthecode.CompanyDirectory.models.responses.EmployeeResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.EmployeeRepository;
import com.mindthecode.CompanyDirectory.strategy.SearchEmployeeStrategy;
import com.mindthecode.CompanyDirectory.strategy.SearchEmployeeStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private SearchEmployeeStrategyFactory factory;

    public GenericResponse<AllEmployeesResponse> getAllEmployees() {
        return new GenericResponse<>(new AllEmployeesResponse(mapper.mapEmployees(repo.findAll())));
    }

    public GenericResponse<AllEmployeesResponse> getEmployeeById(long id) {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        Iterable<Employee> retrievedEmployees = repo.findAll();
        for (Employee employee : retrievedEmployees) {
            if (employee.getId() == id)
                employeeResponses.add(mapper.mapEmployeeToResponse(employee));
        }

        return new GenericResponse<>(new AllEmployeesResponse(employeeResponses));
    }

    public GenericResponse<AllEmployeesResponse> getEmployeeByCriteria(String searchCriteria, long id) {
        SearchEmployeeStrategy strategy = factory.makeStrategyForCriteria(searchCriteria);

        // Fetch employees
        Iterable<Employee> employees = strategy.execute(repo.findAll(), id);
        List<EmployeeResponse> retrievedEmployees = mapper.mapEmployees(employees);

        return new GenericResponse<>(new AllEmployeesResponse(retrievedEmployees));
    }

    public GenericResponse<AllEmployeesResponse> saveEmployee(Employee employee) {
        repo.save(employee);
        List<EmployeeResponse> retrievedEmployees = new ArrayList<>();
        retrievedEmployees.add(mapper.mapEmployeeToResponse(employee));
        return new GenericResponse<>(new AllEmployeesResponse(retrievedEmployees));
    }


    public GenericResponse<AllEmployeesResponse> saveEmployees(Iterable<Employee> employees) {
        repo.saveAll(employees);
        return new GenericResponse<>(new AllEmployeesResponse(mapper.mapEmployees(employees)));
    }

    public GenericResponse<AllEmployeesResponse> deleteEmployee(Employee employee) {
        repo.delete(employee);
        List<EmployeeResponse> retrievedEmployees = new ArrayList<>();
        retrievedEmployees.add(mapper.mapEmployeeToResponse(employee));
        return new GenericResponse<>(new AllEmployeesResponse(retrievedEmployees));
    }


}
