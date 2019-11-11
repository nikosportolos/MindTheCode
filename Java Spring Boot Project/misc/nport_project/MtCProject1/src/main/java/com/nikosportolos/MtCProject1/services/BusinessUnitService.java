package com.nikosportolos.MtCProject1.services;

import com.nikosportolos.MtCProject1.mappers.BusinessUnitMapper;
import com.nikosportolos.MtCProject1.models.BusinessUnit;
import com.nikosportolos.MtCProject1.models.responses.AllBusinessUnitsResponse;
import com.nikosportolos.MtCProject1.models.responses.BusinessUnitResponse;
import com.nikosportolos.MtCProject1.repos.BusinessUnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessUnitService {
    @Autowired
    BusinessUnitRepo repo;

    @Autowired
    BusinessUnitMapper mapper;

    public AllBusinessUnitsResponse getAllBusinessUnits() {
        return new AllBusinessUnitsResponse(mapper.mapBusinessUnits(repo.findAll()));
    }


    public AllBusinessUnitsResponse getBusinessUnitById(long id) {

        List<BusinessUnitResponse> businessUnits = new ArrayList<>();
        Iterable<BusinessUnit> retrievedBusinessUnits = repo.findAll();

        for (BusinessUnit businessUnit : retrievedBusinessUnits) {
            if (businessUnit.getId() == id)
                businessUnits.add(mapper.mapBusinessUnitToResponse(businessUnit));
        }

        return new AllBusinessUnitsResponse(businessUnits);
    }
}
