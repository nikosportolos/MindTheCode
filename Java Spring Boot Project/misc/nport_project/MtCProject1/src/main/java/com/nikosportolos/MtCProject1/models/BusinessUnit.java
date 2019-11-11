package com.nikosportolos.MtCProject1.models;

import javax.persistence.*;

@Entity
@Table(name = "BusinessUnits")
public class BusinessUnit {

    /**
     * Instance variables
     **/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    private Company company;


    /**
     * Constructors
     **/

    public BusinessUnit() {
    }

    public BusinessUnit(long id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * Getters / Setters
     **/

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
