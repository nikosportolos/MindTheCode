package com.mindthecode.CompanyDirectory.models.entities;

import javax.persistence.*;


@Entity
@Table(name = "Departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String departmentName;

    @ManyToOne
    private BusinessUnit businessUnit;


    public Department() {
    }

    public Department(String departmentName, BusinessUnit businessUnit) {
        this.departmentName = departmentName;
        this.businessUnit = businessUnit;
    }

    public Department(long id, String departmentName, BusinessUnit businessUnit) {
        this.id = id;
        this.departmentName = departmentName;
        this.businessUnit = businessUnit;
    }

    public long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }
}
