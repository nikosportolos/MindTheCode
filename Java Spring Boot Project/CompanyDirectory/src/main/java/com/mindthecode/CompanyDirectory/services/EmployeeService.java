package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.EmployeeMapper;
import com.mindthecode.CompanyDirectory.models.responses.AllEmployeesResponse;
import com.mindthecode.CompanyDirectory.models.entities.Employee;
import com.mindthecode.CompanyDirectory.models.responses.EmployeeResponse;
import com.mindthecode.CompanyDirectory.repositories.EmployeeRepo;
import com.mindthecode.CompanyDirectory.strategy.SearchEmployeeStrategy;
import com.mindthecode.CompanyDirectory.strategy.SearchEmployeeStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repo;

    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private SearchEmployeeStrategyFactory factory;

    public AllEmployeesResponse getAllEmployees() {
        return new AllEmployeesResponse(mapper.mapEmployees(repo.findAll()));
    }

    public AllEmployeesResponse getEmployeeById(long id) {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        Iterable<Employee> retrievedEmployees = repo.findAll();
        for (Employee employee : retrievedEmployees) {
            if (employee.getId() == id)
                employeeResponses.add(mapper.mapEmployeeToResponse(employee));
        }
        return new AllEmployeesResponse(employeeResponses);
    }

    public AllEmployeesResponse getEmployeeByCriteria(String searchCriteria, long id) {
        SearchEmployeeStrategy strategy = factory.makeStrategyForCriteria(searchCriteria);
        return new AllEmployeesResponse(mapper.mapEmployees(strategy.execute(repo.findAll(), id)));

//        if (searchCriteria.equalsIgnoreCase(String.valueOf(Enums.SearchCriteria.BUSINESSUNIT)))
//            return getEmployeesByBusinessUnit(id);
//
//        if (searchCriteria.equalsIgnoreCase(String.valueOf(Enums.SearchCriteria.COMPANY)))
//            return getEmployeesByCompany(id);
//
//        if (searchCriteria.equalsIgnoreCase(String.valueOf(Enums.SearchCriteria.DEPARTMENT)))
//            return getEmployeesByDepartment(id);
//
//        if (searchCriteria.equalsIgnoreCase(String.valueOf(Enums.SearchCriteria.UNIT)))
//            return getEmployeesByUnit(id);
//
//        List<EmployeeResponse> employeeResponses = new ArrayList<>();
//        return new AllEmployeesResponse(employeeResponses);
    }

    private AllEmployeesResponse getEmployeesByUnit(long id) {
        System.out.println("###Loading employee by unit: " + id);
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        Iterable<Employee> retrievedEmployees = repo.findAll();
        for (Employee employee : retrievedEmployees) {
            if (employee.getPosition().getUnit().getId() == id)
                employeeResponses.add(mapper.mapEmployeeToResponse(employee));
        }
        return new AllEmployeesResponse(employeeResponses);
    }

    private AllEmployeesResponse getEmployeesByBusinessUnit(long id) {
        System.out.println("###Loading employee by business unit: " + id);
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        Iterable<Employee> retrievedEmployees = repo.findAll();
        for (Employee employee : retrievedEmployees) {
            if (employee.getPosition().getUnit().getDepartment().getBusinessUnit().getId() == id)
                employeeResponses.add(mapper.mapEmployeeToResponse(employee));
        }
        return new AllEmployeesResponse(employeeResponses);
    }

    private AllEmployeesResponse getEmployeesByCompany(long id) {
        System.out.println("###Loading employee by company: " + id);
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        Iterable<Employee> retrievedEmployees = repo.findAll();
        for (Employee employee : retrievedEmployees) {
            if (employee.getPosition().getUnit().getDepartment().getBusinessUnit().getCompany().getCompanyId() == id)
                employeeResponses.add(mapper.mapEmployeeToResponse(employee));
        }
        return new AllEmployeesResponse(employeeResponses);
    }

    private AllEmployeesResponse getEmployeesByDepartment(long id) {
        System.out.println("###Loading employee by department: " + id);
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        Iterable<Employee> retrievedEmployees = repo.findAll();
        for (Employee employee : retrievedEmployees) {
            if (employee.getPosition().getUnit().getDepartment().getId() == id)
                employeeResponses.add(mapper.mapEmployeeToResponse(employee));
        }
        return new AllEmployeesResponse(employeeResponses);
    }

}
