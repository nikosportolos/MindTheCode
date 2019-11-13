package com.mindthecode.CompanyDirectory;

import javax.persistence.*;

@Entity
@Table(name = "BusinessUnits")
public class BusinessUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String NameOfBusinessUnit;

    /*@ManyToOne
    private Company company;*/

    public BusinessUnit() {
    }

    public BusinessUnit(String nameOfBusinessUnit) {
        NameOfBusinessUnit = nameOfBusinessUnit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfBusinessUnit() {
        return NameOfBusinessUnit;
    }

    public void setNameOfBusinessUnit(String nameOfBusinessUnit) {
        NameOfBusinessUnit = nameOfBusinessUnit;
    }

   /* public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }*/
}
