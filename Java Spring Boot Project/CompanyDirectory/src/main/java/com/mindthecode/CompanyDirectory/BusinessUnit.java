package com.mindthecode.CompanyDirectory;

import javax.persistence.*;

@Entity
@Table(name = "BusinessUnits")
public class BusinessUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nameOfBusinessUnit;

    /*@ManyToOne
    private Company company;*/

    public BusinessUnit() {
    }

    public BusinessUnit(String nameOfBusinessUnit) {
        this.nameOfBusinessUnit = nameOfBusinessUnit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfBusinessUnit() {
        return nameOfBusinessUnit;
    }

    public void setNameOfBusinessUnit(String nameOfBusinessUnit) {
        this.nameOfBusinessUnit = nameOfBusinessUnit;
    }

    /* public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }*/
}
