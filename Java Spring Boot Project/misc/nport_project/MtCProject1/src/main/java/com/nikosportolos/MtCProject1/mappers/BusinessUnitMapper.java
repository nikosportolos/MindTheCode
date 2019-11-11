package com.nikosportolos.MtCProject1.mappers;

import com.nikosportolos.MtCProject1.models.BusinessUnit;
import com.nikosportolos.MtCProject1.models.responses.BusinessUnitResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class BusinessUnitMapper {

    public List<BusinessUnitResponse> mapBusinessUnits(Iterable<BusinessUnit> all) {
        List<BusinessUnitResponse> response = new ArrayList<>();
        for (BusinessUnit businessUnit : all) {
            response.add(mapBusinessUnitToResponse(businessUnit));
        }
        return response;
    }

    public BusinessUnitResponse mapBusinessUnitToResponse(BusinessUnit businessUnit) {
        return new BusinessUnitResponse(businessUnit.getId(), businessUnit.getName(), businessUnit.getCompany());
    }
}
