package com.mindthecode.CompanyDirectory.models.responses;

import com.mindthecode.CompanyDirectory.common.Enums;
import com.mindthecode.CompanyDirectory.models.entities.Position;

import java.util.Date;

public class EmployeeResponse {

    /**
     * Instance variables
     **/
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Date hireDate;
    private Date departureDate;
    private Enums.EmployeeStatus status;
    private Enums.ContractType contractType;
    private Position position;

    /**
     * Constructors
     **/

    public EmployeeResponse() {
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

    public EmployeeResponse(long id, String firstName, String lastName) {
        this.id = id;
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

    public EmployeeResponse(long id, String firstName, String lastName, String address, String phoneNumber, Date hireDate, Date departureDate, Enums.EmployeeStatus status, Enums.ContractType contractType, Position position) {
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

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", departureDate=" + departureDate +
                ", status=" + status +
                ", contractType=" + contractType +
                ", position=" + position +
                '}';
    }
}
