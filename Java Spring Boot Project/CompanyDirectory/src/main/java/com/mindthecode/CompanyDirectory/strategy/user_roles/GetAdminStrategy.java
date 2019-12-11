package com.mindthecode.CompanyDirectory.strategy.user_roles;

import com.mindthecode.CompanyDirectory.common.Enums;

public class GetAdminStrategy implements GetUserRoleStrategy {
    @Override
    public String execute() {
        return Enums.UserRole.ADMIN.toString();
    }
}
