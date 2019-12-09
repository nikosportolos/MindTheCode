package com.mindthecode.CompanyDirectory.strategy.user_roles;

import com.mindthecode.CompanyDirectory.common.Enums;

public class GetUnitManagerStrategy implements GetUserRoleStrategy {
    @Override
    public String execute() {

        return Enums.UserRole.ADMIN.toString() + "," +
                Enums.UserRole.COMPANY_MANAGER.toString() + "," +
                Enums.UserRole.BUSINESS_UNIT_MANAGER.toString() + "," +
                Enums.UserRole.DEPARTMENT_MANAGER.toString() + "," +
                Enums.UserRole.UNIT_MANAGER.toString();

    }
}
