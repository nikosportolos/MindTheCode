package com.mindthecode.CompanyDirectory;

import javax.persistence.*;

@Entity
@Table(name = "Positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private AvailablePositions name;

    /*uncomment when Unit is ready*/
  //  @ManyToOne
  //  private Unit unit;

    public Position() {
    }

    public Position(AvailablePositions name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AvailablePositions getName() {
        return name;
    }

    public void setName(AvailablePositions name) {
        this.name = name;
    }

    /*uncomment when Unit is ready*/
    /*public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }*/

    /*Needs to be in Enums file*/
    public enum AvailablePositions{
        JuniorAnalyst("Junior Analyst"),
        SeniorAnalyst("Senior Analyst"),
        JuniorDeveloper("Junior Developer"),
        SeniorDeveloper("Senior Developer"),
        Manager("Manager");

        private String text;

        AvailablePositions(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static AvailablePositions fromString(String text) {
            for (AvailablePositions b : AvailablePositions.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }
}

