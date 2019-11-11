package com.nikosportolos.MtCProject1.services;

import com.nikosportolos.MtCProject1.mappers.PositionMapper;
import com.nikosportolos.MtCProject1.models.Employee;
import com.nikosportolos.MtCProject1.models.Position;
import com.nikosportolos.MtCProject1.models.responses.AllEmployeesResponse;
import com.nikosportolos.MtCProject1.models.responses.AllPositionsResponse;
import com.nikosportolos.MtCProject1.models.responses.EmployeeResponse;
import com.nikosportolos.MtCProject1.models.responses.PositionResponse;
import com.nikosportolos.MtCProject1.repos.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionRepo repo;

    @Autowired
    PositionMapper mapper;

    public AllPositionsResponse getAllPositions() {
        return new AllPositionsResponse(mapper.mapPositions(repo.findAll()));
    }

    public AllPositionsResponse getPositionById(long id) {
        List<PositionResponse> positionResponses = new ArrayList<>();
        Iterable<Position> retrievedPositions = repo.findAll();
        for (Position position : retrievedPositions) {
            if (position.getId() == id)
                positionResponses.add(mapper.mapPositionToResponse(position));
        }
        return new AllPositionsResponse(positionResponses);
    }
}
