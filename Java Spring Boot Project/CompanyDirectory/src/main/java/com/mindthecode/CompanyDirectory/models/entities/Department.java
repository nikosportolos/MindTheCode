package com.mindthecode.CompanyDirectory.models.entities;

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

    public Department() {
    }

    public Department(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
