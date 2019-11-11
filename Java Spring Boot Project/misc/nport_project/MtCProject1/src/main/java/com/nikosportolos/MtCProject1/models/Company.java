package com.nikosportolos.MtCProject1.models;

import javax.persistence.*;

@Entity
@Table(name = "Companies")
public class Company {

    /**
     * Instance variables
     **/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;


    /**
     * Constructors
     **/

    public Company() {
    }

    public Company(String name) {
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

}
