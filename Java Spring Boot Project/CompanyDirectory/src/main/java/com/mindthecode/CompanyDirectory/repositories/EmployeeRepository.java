package com.mindthecode.CompanyDirectory.repositories;

import com.mindthecode.CompanyDirectory.models.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
