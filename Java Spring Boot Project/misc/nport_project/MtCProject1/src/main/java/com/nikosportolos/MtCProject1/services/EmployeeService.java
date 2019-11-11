package com.nikosportolos.MtCProject1.services;

import com.nikosportolos.MtCProject1.common.Enums;
import com.nikosportolos.MtCProject1.mappers.EmployeeMapper;
import com.nikosportolos.MtCProject1.models.Department;
import com.nikosportolos.MtCProject1.models.Employee;
import com.nikosportolos.MtCProject1.models.responses.AllDepartmentResponse;
import com.nikosportolos.MtCProject1.models.responses.AllEmployeesResponse;
import com.nikosportolos.MtCProject1.models.responses.DepartmentResponse;
import com.nikosportolos.MtCProject1.models.responses.EmployeeResponse;
import com.nikosportolos.MtCProject1.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo repo;

    @Autowired
    EmployeeMapper mapper;

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

        if (searchCriteria.toUpperCase().equals(String.valueOf(Enums.SearchCriteria.BUSINESSUNIT)))
            return getEmployeesByBusinessUnit(id);

        if (searchCriteria.toUpperCase().equals(String.valueOf(Enums.SearchCriteria.COMPANY)))
            return getEmployeesByCompany(id);

        if (searchCriteria.toUpperCase().equals(String.valueOf(Enums.SearchCriteria.DEPARTMENT)))
            return getEmployeesByDepartment(id);

        if (searchCriteria.toUpperCase().equals(String.valueOf(Enums.SearchCriteria.UNIT)))
            return getEmployeesByUnit(id);

        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        return new AllEmployeesResponse(employeeResponses);
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
            if (employee.getPosition().getUnit().getDepartment().getBusinessUnit().getCompany().getId() == id)
                employeeResponses.add(mapper.mapEmployeeToResponse(employee));
        }
        return new AllEmployeesResponse(employeeResponses);
    }


    public AllEmployeesResponse getEmployeesByDepartment(long id) {
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
