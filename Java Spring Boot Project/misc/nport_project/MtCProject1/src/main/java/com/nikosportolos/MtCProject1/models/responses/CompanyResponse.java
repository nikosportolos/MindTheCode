package com.nikosportolos.MtCProject1.models.responses;

public class CompanyResponse {

    /**
     * Instance variables
     **/

    private long id;
    private String name;


    /**
     * Constructors
     **/

    public CompanyResponse() {
    }

    public CompanyResponse(String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyResponse(long id, String name) {
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
