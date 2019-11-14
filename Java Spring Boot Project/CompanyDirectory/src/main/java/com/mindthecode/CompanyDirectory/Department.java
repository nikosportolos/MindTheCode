package com.mindthecode.CompanyDirectory;

import javax.persistence.*;


@Entity
@Table(name = "Departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String departmentName;


    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
