package com.nikosportolos.MtCProject1.services;

import com.nikosportolos.MtCProject1.mappers.UnitMapper;
import com.nikosportolos.MtCProject1.models.Position;
import com.nikosportolos.MtCProject1.models.Unit;
import com.nikosportolos.MtCProject1.models.responses.AllPositionsResponse;
import com.nikosportolos.MtCProject1.models.responses.AllUnitsResponse;
import com.nikosportolos.MtCProject1.models.responses.PositionResponse;
import com.nikosportolos.MtCProject1.models.responses.UnitResponse;
import com.nikosportolos.MtCProject1.repos.UnitRepo;
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
