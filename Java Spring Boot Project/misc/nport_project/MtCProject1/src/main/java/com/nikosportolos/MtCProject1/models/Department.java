package com.nikosportolos.MtCProject1.models;

import javax.persistence.*;

@Entity
@Table(name = "Departments")
public class Department {

    /**
     * Instance variables
     **/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    private BusinessUnit businessUnit;


    /**
     * Constructors
     **/

    public Department() {
    }

    public Department(String name) {
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

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }
}
