package com.mindthecode.CompanyDirectory;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessUnitMapper {

    public List<BusinessUnitResponse> mapBusinessUnits(Iterable<BusinessUnit> all){
        List<BusinessUnitResponse> response = new ArrayList<>();
        for(BusinessUnit businessUnit : all){
            response.add(mapBusinessinessUnitToResponse(businessUnit));
        }
        return response;
    }

    public BusinessUnitResponse mapBusinessinessUnitToResponse(BusinessUnit businessUnit) {
        return new BusinessUnitResponse(
                businessUnit.getId(),
                businessUnit.getNameOfBusinessUnit()
               // businessUnit.getCompany()
        );
    }


}
