package com.mindthecode.CompanyDirectory;

import java.util.ArrayList;
import java.util.List;

public class BusinessUnitMapper {

    public List<BusinessUnitResponse> mapBusinessUnits(Iterable<BusinessUnit> all){
        List<BusinessUnitResponse> businessUnits = new ArrayList<>();
        for(BusinessUnit businessUnit : all){
            BusinessUnitResponse businessUnitResponse = mapBusinessinessUnitResponseFromBusinessUnit(businessUnit);
            businessUnits.add(businessUnitResponse);
        }
        return businessUnits;
    }

    public BusinessUnitResponse mapBusinessinessUnitResponseFromBusinessUnit(BusinessUnit businessUnit) {
        return new BusinessUnitResponse(
                businessUnit.getId(),
                businessUnit.getNameOfBusinessUnit()
               // businessUnit.getCompany()
        );
    }


}
