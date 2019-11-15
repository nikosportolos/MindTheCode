package com.mindthecode.CompanyDirectory.strategy;

import com.mindthecode.CompanyDirectory.models.entities.Employee;

public interface SearchEmployeeStrategy {

    Iterable<Employee> execute(Iterable<Employee> allEmployees, Long criteriaId);
}
