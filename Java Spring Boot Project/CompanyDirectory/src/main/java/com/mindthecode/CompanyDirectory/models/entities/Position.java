package com.mindthecode.CompanyDirectory.models.entities;

import com.mindthecode.CompanyDirectory.common.Enums;

import javax.persistence.*;

@Entity
@Table(name = "Positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Unit unit;

    public Position() {
    }

    public Position(String name) {
        this.name = name;
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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }


    public class AvailablePositions {
        public class JuniorAnalyst {
        }
    }
}

