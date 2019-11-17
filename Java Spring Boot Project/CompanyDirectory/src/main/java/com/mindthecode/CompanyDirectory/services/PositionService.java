package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.PositionMapper;
import com.mindthecode.CompanyDirectory.models.entities.Position;
import com.mindthecode.CompanyDirectory.models.responses.AllPositionsResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.models.responses.PositionResponse;
import com.mindthecode.CompanyDirectory.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository repository;

    @Autowired
    private PositionMapper mapper;

    public GenericResponse<AllPositionsResponse> getAllPositions() {
        List<PositionResponse> positions = mapper.mapPositions(repository.findAll());
        if (positions == null || positions.size() > 0)
            return new GenericResponse<>(new AllPositionsResponse(positions));

        return new GenericResponse<>(new ErrorResponse(0, "Error", "No positions were found"));
    }

    public GenericResponse<PositionResponse> getPositionById(long id) {
        Iterable<Position> retrievedPositions = repository.findAll();
        for (Position position : retrievedPositions) {
            if (position.getId() == id)
                return new GenericResponse<>(mapper.mapPositionToResponse(position));
        }
        return new GenericResponse<>(new ErrorResponse(0, "Unknown position", "No position found with id " + id));
    }


    public GenericResponse<String> savePosition(Position position) {
        try {
            repository.save(position);
            return new GenericResponse<>("company position #" + position.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save position"));
        }
    }

}
