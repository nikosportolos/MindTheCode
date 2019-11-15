package com.nikosportolos.MtCProject1.repos;

import com.nikosportolos.MtCProject1.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
