package com.mindthecode.CompanyDirectory.strategy;

import com.mindthecode.CompanyDirectory.models.entities.Employee;

import java.util.List;

public interface SearchEmployeeStrategy {

    List<Employee> execute(Iterable<Employee> allEmployees, Long criteriaId);
}
