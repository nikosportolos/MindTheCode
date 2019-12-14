package com.mindthecode.CompanyDirectory.Department;

import com.mindthecode.CompanyDirectory.mappers.DepartmentMapper;
import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import com.mindthecode.CompanyDirectory.models.entities.Department;
import com.mindthecode.CompanyDirectory.models.responses.*;
import com.mindthecode.CompanyDirectory.repositories.BusinessUnitRepository;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import com.mindthecode.CompanyDirectory.repositories.DepartmentRepository;
import com.mindthecode.CompanyDirectory.services.DepartmentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class DepartmentServiceShould {

    private DepartmentService service;

    private DepartmentResponse departmentResponseFromMapper;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper mapper;

    @Mock
    private BusinessUnitRepository businessUnitRepository;

    @Mock
    private CompanyRepository companyRepository;

    private Iterable<Department> mockedDepartments = new ArrayList<Department>() {
        {
            add(new Department(6, "Application Management", new BusinessUnit(1, "Financial Division", new Company("Unisystems"))));
            add(new Department(7, "Credit Control Department", new BusinessUnit(2, "Sales", new Company("InfoQuest"))));
        }
    };

    private List<DepartmentResponse> mockedDepartmentResponsess = new ArrayList<DepartmentResponse>() {
        {
            add(new DepartmentResponse(6, "Application Management", new BusinessUnit(1, "Financial Division", new Company("Unisystems"))));
            add(new DepartmentResponse(7, "Credit Control Department", new BusinessUnit(2, "Sales", new Company("InfoQuest"))));
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(departmentRepository.findAll()).thenReturn(mockedDepartments);

        when(mapper.mapDepartments(mockedDepartments)).thenReturn(mockedDepartmentResponsess);

        service = new DepartmentService(mapper, departmentRepository);
        GenericResponse<AllDepartmentsResponse> mockResponse = new GenericResponse<>(new AllDepartmentsResponse(mockedDepartmentResponsess));
        System.out.println("mockResponse: " + mockResponse.toString());
        when(service.getAllDepartments().getData().getDepartments()).thenReturn(mockedDepartmentResponsess);

        departmentResponseFromMapper = new DepartmentResponse(1, "name", new BusinessUnit(1, "Sales", new Company("InfoQuest")));
        when(mapper.mapDepartmentToResponse(any())).thenReturn(departmentResponseFromMapper);
    }

    @Test
    public void retrieveDepartmentsFromRepository() {
        service.getAllDepartments();
        Mockito.verify(departmentRepository, times(2)).findAll();
    }

    @Test
    public void usesBusinessUnitMapper() {
        var businessUnits = service.getAllDepartments();
        Mockito.verify(mapper, times(1)).mapDepartments(any());
    }

    @Test
    public void returnsListOfBusinessUnitResponse() {
        GenericResponse<AllDepartmentsResponse> output = service.getAllDepartments();
        System.out.println("Output: " + output.toString());

        List<DepartmentResponse> responses = output.getData().getDepartments();
        System.out.println("Responses: " + responses.size());
        Assert.assertEquals(2, responses.size());

        Assert.assertEquals(service.getAllDepartments().getData().getDepartments(), mockedDepartmentResponsess);
    }

}

