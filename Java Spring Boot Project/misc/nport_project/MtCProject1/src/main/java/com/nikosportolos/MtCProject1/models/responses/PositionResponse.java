package com.nikosportolos.MtCProject1.models.responses;

import com.nikosportolos.MtCProject1.models.Unit;

public class PositionResponse {

    /**
     * Instance variables
     **/

    private long id;
    private String name;
    private Unit unit;

    /**
     * Constructors
     **/

    public PositionResponse() {
    }


    public PositionResponse(long id, String name, Unit unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
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
