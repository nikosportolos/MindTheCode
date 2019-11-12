package com.mindthecode.CompanyDirectory.models.entities;


import javax.persistence.*;

@Entity
@Table(name = "Units")
public class Unit {

    /**
     * Instance variables
     **/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    private Department department;

    /**
     * Constructors
     **/

    public Unit() {
    }

    public Unit(String name) {
        this.id = id;
        this.name = name;
    }

    public Unit(long id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
