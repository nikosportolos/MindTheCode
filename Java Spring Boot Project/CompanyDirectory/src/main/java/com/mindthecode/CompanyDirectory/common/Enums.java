package com.mindthecode.CompanyDirectory.common;

public class Enums {

    public enum EmployeeStatus {Unknown, Active, Inactive}

    public enum ContractType {Unknown, UniSystems, External}

    public enum SearchCriteria {UNIT, BUSINESSUNIT, COMPANY, DEPARTMENT}

    public enum TaskStatus {NEW, STARTED, DONE}

    public enum TaskDifficulty {EASY, MEDIUM, HARD}

    public enum AvailablePositions {
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
