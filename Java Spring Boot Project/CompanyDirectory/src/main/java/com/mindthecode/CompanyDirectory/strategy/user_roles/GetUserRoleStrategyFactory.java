package com.mindthecode.CompanyDirectory.strategy.user_roles;

import com.mindthecode.CompanyDirectory.common.Enums;
import org.springframework.stereotype.Component;

@Component
public class GetUserRoleStrategyFactory {

    public GetUserRoleStrategy makeStrategyForCriteria(Enums.UserRole role) {
        switch (role) {
            case ADMIN:
                return new GetAdminStrategy();
            case COMPANY_MANAGER:
                return new GetCompanyManagerStrategy();
            case BUSINESS_UNIT_MANAGER:
                return new GetBusinessUnitManagerStrategy();
            case DEPARTMENT_MANAGER:
                return new GetDepartmentManagerStrategy();
            case UNIT_MANAGER:
                return new GetUnitManagerStrategy();
            default:
                return new GetEmployeeStrategy();
        }
    }
}
