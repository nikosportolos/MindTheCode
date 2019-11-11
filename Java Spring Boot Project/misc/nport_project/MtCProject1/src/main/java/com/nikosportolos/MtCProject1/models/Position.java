package com.nikosportolos.MtCProject1.models;

import javax.persistence.*;

@Entity
@Table(name = "Positions")
public class Position {

    /**
     * Instance variables
     **/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    private Unit unit;

    /**
     * Constructors
     **/

    public Position() {
    }

    public Position(String name) {
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
