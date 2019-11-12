package com.nikosportolos.MtCProject1.strategy;

import com.nikosportolos.MtCProject1.models.Employee;

import java.util.List;

public interface SearchEmployeeStrategy {

    List<Employee> execute(Iterable<Employee> allEmployees, Long criteriaId);
}
