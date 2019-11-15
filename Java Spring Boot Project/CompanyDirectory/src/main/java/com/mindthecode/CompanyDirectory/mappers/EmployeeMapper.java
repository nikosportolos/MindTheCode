package com.nikosportolos.MtCProject1.mappers;

import com.nikosportolos.MtCProject1.models.Employee;
import com.nikosportolos.MtCProject1.models.responses.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {
    public List<EmployeeResponse> mapEmployees(Iterable<Employee> all) {
        List<EmployeeResponse> response = new ArrayList<>();
        for (Employee employee : all) {
            response.add(mapEmployeeToResponse(employee));
        }
        return response;
    }

    public EmployeeResponse mapEmployeeToResponse(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getAddress(), employee.getPhoneNumber(), employee.getHireDate(), employee.getDepartureDate(), employee.getStatus(), employee.getContractType(), employee.getPosition());
    }
}
