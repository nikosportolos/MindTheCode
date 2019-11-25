package com.mindthecode.CompanyDirectory.strategy.user_roles;

import com.mindthecode.CompanyDirectory.common.Enums;

public class GetCompanyManagerStrategy implements GetUserRoleStrategy {
    @Override
    public String execute() {
        return Enums.UserRole.ADMIN.toString() + "," + Enums.UserRole.COMPANY_MANAGER.toString();
    }
}
