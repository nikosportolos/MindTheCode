package com.mindthecode.CompanyDirectory.strategy.search_employee;

import org.springframework.stereotype.Component;

@Component
public class SearchEmployeeStrategyFactory {

    public SearchEmployeeStrategy makeStrategyForCriteria(String criteria) {
        switch (criteria) {
            case "unit":
                return new SearchEmployeeByUnitStrategy();
            case "businessUnit":
                return new SearchEmployeeByBusinessUnitStrategy();
            case "department":
                return new SearchEmployeeByDepartmentStrategy();
            case "company":
                return new SearchEmployeeByCompanyStrategy();
            default:
                return new SearchEmployeeByUnitStrategy();
        }
    }
}
