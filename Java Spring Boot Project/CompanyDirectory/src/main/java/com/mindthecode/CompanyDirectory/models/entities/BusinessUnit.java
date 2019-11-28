package com.mindthecode.CompanyDirectory.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "BusinessUnits")
public class BusinessUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    private Company company;

    public BusinessUnit() {
    }

    public BusinessUnit(long id, String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public BusinessUnit(String name) {
        this.name = name;
    }

    public BusinessUnit(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
