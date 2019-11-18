package com.mindthecode.CompanyDirectory.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "Departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    private BusinessUnit businessUnit;


    public Department() {
    }

    public Department(String name, BusinessUnit businessUnit) {
        this.name = name;
        this.businessUnit = businessUnit;
    }

    public Department(long id, String name, BusinessUnit businessUnit) {
        this.id = id;
        this.name = name;
        this.businessUnit = businessUnit;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }
}
