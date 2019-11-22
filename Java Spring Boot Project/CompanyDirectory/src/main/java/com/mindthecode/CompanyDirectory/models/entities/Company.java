package com.mindthecode.CompanyDirectory.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "Companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String companyName;

    public Company() {
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company(long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

