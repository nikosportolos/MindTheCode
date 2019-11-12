package com.mindthecode.CompanyDirectory.services;


import com.mindthecode.CompanyDirectory.mappers.UnitMapper;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.AllUnitsResponse;
import com.mindthecode.CompanyDirectory.models.responses.UnitResponse;
import com.mindthecode.CompanyDirectory.repositories.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitService {

    @Autowired
    UnitRepo repo;

    @Autowired
    UnitMapper mapper;

    public AllUnitsResponse getAllUnits() {
        return new AllUnitsResponse(mapper.mapUnits(repo.findAll()));
    }

    public AllUnitsResponse getUnitById(long id) {
        List<UnitResponse> positionUnits = new ArrayList<>();
        Iterable<Unit> retrievedUnits = repo.findAll();
        for (Unit position : retrievedUnits) {
            if (position.getId() == id)
                positionUnits.add(mapper.mapUnitToResponse(position));
        }
        return new AllUnitsResponse(positionUnits);
    }
}
