package com.mindthecode.CompanyDirectory.models.entities;

import com.mindthecode.CompanyDirectory.common.Enums;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employee {

    /**
     * Instance variables
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Date hireDate;
    private Date departureDate;
    private Enums.EmployeeStatus status;
    private Enums.ContractType contractType;

    @ManyToOne
    private Position position;

    @ManyToMany
    private List<Task> tasks;

    /**
     * Constructors
     **/

    public Employee() {
        this.firstName = "N/A";
        this.lastName = "N/A";
        this.address = "N/A";
        this.phoneNumber = "N/A";
        this.hireDate = new Date(Long.MIN_VALUE);
        this.departureDate = new Date(Long.MIN_VALUE);
        this.status = Enums.EmployeeStatus.Unknown;
        this.contractType = Enums.ContractType.Unknown;
        this.position = new Position();
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = "N/A";
        this.phoneNumber = "N/A";
        this.hireDate = new Date(Long.MIN_VALUE);
        this.departureDate = new Date(Long.MIN_VALUE);
        this.status = Enums.EmployeeStatus.Unknown;
        this.contractType = Enums.ContractType.Unknown;
        this.position = new Position();
    }

    public Employee(String firstName, String lastName, String address, String phoneNumber, Date hireDate, Date departureDate, Enums.EmployeeStatus status, Enums.ContractType contractType, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.departureDate = departureDate;
        this.status = status;
        this.contractType = contractType;
        this.position = position;
    }

    public Employee(long id, String firstName, String lastName, String address, String phoneNumber, Date hireDate, Date departureDate, Enums.EmployeeStatus status, Enums.ContractType contractType, Position position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.departureDate = departureDate;
        this.status = status;
        this.contractType = contractType;
        this.position = position;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Enums.EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.EmployeeStatus status) {
        this.status = status;
    }

    public Enums.ContractType getContractType() {
        return contractType;
    }

    public void setContractType(Enums.ContractType contractType) {
        this.contractType = contractType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Public Methods
     **/
    public void updateEmployee(Employee targetEmp) {
        this.firstName = targetEmp.firstName;
        this.lastName = targetEmp.lastName;
        this.address = targetEmp.address;
        this.phoneNumber = targetEmp.phoneNumber;
        this.hireDate = targetEmp.hireDate;
        this.departureDate = targetEmp.departureDate;
        this.status = targetEmp.status;
        this.contractType = targetEmp.contractType;
        this.position = targetEmp.position;
    }
}