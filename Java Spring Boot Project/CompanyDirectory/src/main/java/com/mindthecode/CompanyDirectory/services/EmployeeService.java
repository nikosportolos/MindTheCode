package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.EmployeeMapper;
import com.mindthecode.CompanyDirectory.models.responses.AllEmployeesResponse;
import com.mindthecode.CompanyDirectory.models.entities.Employee;
import com.mindthecode.CompanyDirectory.models.responses.EmployeeResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.EmployeeRepository;
import com.mindthecode.CompanyDirectory.strategy.search_employee.SearchEmployeeStrategy;
import com.mindthecode.CompanyDirectory.strategy.search_employee.SearchEmployeeStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private SearchEmployeeStrategyFactory factory;

    public GenericResponse<AllEmployeesResponse> getAllEmployees() {
        List<EmployeeResponse> employees = mapper.mapEmployees(repository.findAll());
        if (employees == null || employees.size() > 0)
            return new GenericResponse<>(new AllEmployeesResponse(employees));

        return new GenericResponse<>(new ErrorResponse(0, "Error", "No employees were found"));
    }

    public GenericResponse<AllEmployeesResponse> getEmployeeById(long id) {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        Iterable<Employee> retrievedEmployees = repository.findAll();

        for (Employee employee : retrievedEmployees) {
            if (employee.getId() == id)
                employeeResponses.add(mapper.mapEmployeeToResponse(employee));
        }

        if (employeeResponses.size() == 0)
            return new GenericResponse<>(new ErrorResponse(0, "Unknown employee", "No employee found with id " + id));

        return new GenericResponse<>(new AllEmployeesResponse(employeeResponses));
    }

    public GenericResponse<AllEmployeesResponse> getEmployeeByCriteria(String searchCriteria, long id) {
        SearchEmployeeStrategy strategy = factory.makeStrategyForCriteria(searchCriteria);

        // Fetch employees
        Iterable<Employee> employees = strategy.execute(repository.findAll(), id);
        List<EmployeeResponse> retrievedEmployees = mapper.mapEmployees(employees);

        return new GenericResponse<>(new AllEmployeesResponse(retrievedEmployees));
    }

    public GenericResponse<String> saveEmployee(Employee employee) {
        try {
            repository.save(employee);
            return new GenericResponse<>("Saved employee #" + employee.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save employee"));
        }
    }

    public GenericResponse<String> saveEmployees(Iterable<Employee> employees) {
        try {
            repository.saveAll(employees);
            return new GenericResponse<>("Saved employees");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save employee"));
        }
    }

    public GenericResponse<String> deleteEmployee(Employee employee) {
        try {
            repository.delete(employee);
            return new GenericResponse<>("Deleted employee #" + employee.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete employee"));
        }
    }

    public GenericResponse<String> deleteEmployees(Iterable<Employee> employees) {
        try {
            repository.deleteAll(employees);
            return new GenericResponse<>("Deleted employees");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete employees"));
        }
    }

    public GenericResponse<String> deleteAllEmployees() {
        try {
            repository.deleteAll();
            return new GenericResponse<>("Deleted all employees");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete all employee"));
        }
    }


}
