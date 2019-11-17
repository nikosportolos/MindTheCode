package com.mindthecode.CompanyDirectory;

import com.mindthecode.CompanyDirectory.common.Enums;
import com.mindthecode.CompanyDirectory.models.entities.*;
import com.mindthecode.CompanyDirectory.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class CompanyDirectoryApplication implements CommandLineRunner {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private BusinessUnitService businessUnitService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(CompanyDirectoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        addMockData();
    }

    private void addMockData() throws ParseException {
        // Add companies
        Company company1 = new Company("MindTheCode");
        Company company2 = new Company("UniSystems");
        companyService.saveCompany(company1);
        companyService.saveCompany(company2);

        // Add business units
        BusinessUnit businessUnit1 = new BusinessUnit("Software Technical Division", company1);
        BusinessUnit businessUnit2 = new BusinessUnit("Financial Division", company1);
        BusinessUnit businessUnit3 = new BusinessUnit("Academic Division", company2);
        businessUnitService.saveBusinessUnit(businessUnit1);
        businessUnitService.saveBusinessUnit(businessUnit2);
        businessUnitService.saveBusinessUnit(businessUnit3);

        // Add departments
        Department department1 = new Department("Application Management", businessUnit1);
        Department department2 = new Department("Credit Control Department", businessUnit2);
        Department department3 = new Department("Resource Management Solutions & S/W Production Practices", businessUnit1);
        Department department4 = new Department("Application Management", businessUnit3);
        Department department5 = new Department("IT ", businessUnit3);
        departmentService.saveDepartment(department1);
        departmentService.saveDepartment(department2);
        departmentService.saveDepartment(department3);
        departmentService.saveDepartment(department4);
        departmentService.saveDepartment(department5);

        // Add units
        Unit unit1 = new Unit("Resource Management Solutions", department3);
        Unit unit2 = new Unit("Accounting", department2);
        Unit unit3 = new Unit("System Engineering Unit", department1);
        Unit unit4 = new Unit("Academic Faculty", department1);
        Unit unit5 = new Unit("Support", department1);
        unitService.saveUnit(unit1);
        unitService.saveUnit(unit2);
        unitService.saveUnit(unit3);
        unitService.saveUnit(unit4);
        unitService.saveUnit(unit5);

        // Add positions
        Position position1 = new Position("Software Engineer", unit1);
        Position position2 = new Position("Data Analyst", unit1);
        Position position3 = new Position("Senior Consultant", unit1);
        Position position4 = new Position("First level Support", unit5);
        Position position5 = new Position("Field Engineer", unit3);
        Position position6 = new Position("Instructor", unit4);
        Position position7 = new Position("Academic Director", unit4);
        positionService.savePosition(position1);
        positionService.savePosition(position2);
        positionService.savePosition(position3);
        positionService.savePosition(position4);
        positionService.savePosition(position5);
        positionService.savePosition(position6);
        positionService.savePosition(position7);

        // Add employees
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Employee employee1 = new Employee( "John", "Doe", "Wall St", "0123456789",
                formatter.parse("2010-05-20"),formatter.parse("2010-05-20"),
                Enums.EmployeeStatus.Active, Enums.ContractType.External, position5);

        Employee employee2 = new Employee( "Sarah", "James", "Wall St", "6947368142",
                formatter.parse("2014-05-20"),formatter.parse("2018-02-20"),
                Enums.EmployeeStatus.Inactive, Enums.ContractType.External, position3);

        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);
    }
}
