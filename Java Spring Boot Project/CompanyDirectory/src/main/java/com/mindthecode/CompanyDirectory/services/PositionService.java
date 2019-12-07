package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.BusinessUnitMapper;
import com.mindthecode.CompanyDirectory.mappers.PositionMapper;
import com.mindthecode.CompanyDirectory.models.entities.Position;
import com.mindthecode.CompanyDirectory.models.responses.AllPositionsResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.models.responses.PositionResponse;
import com.mindthecode.CompanyDirectory.repositories.BusinessUnitRepository;
import com.mindthecode.CompanyDirectory.repositories.CompanyRepository;
import com.mindthecode.CompanyDirectory.repositories.PositionRepository;
import com.mindthecode.CompanyDirectory.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository repository;

    @Autowired
    private PositionMapper mapper;

    @Autowired
    private UnitRepository unitRepository;

    public PositionService(PositionMapper mapper, PositionRepository repository, UnitRepository unitRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.unitRepository = unitRepository;
    }

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
            return new GenericResponse<>("Saved position #" + position.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save position"));
        }
    }

    public GenericResponse<String> savePositions(Iterable<Position> positions) {
        try {
            repository.saveAll(positions);
            return new GenericResponse<>("Saved positions");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save position"));
        }
    }

    public GenericResponse<String> deletePosition(Position position) {
        try {
            repository.delete(position);
            return new GenericResponse<>("Deleted position #" + position.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete position #" + position.getId()));
        }
    }

    public GenericResponse<String> deletePositions(Iterable<Position> positions) {
        try {
            repository.deleteAll(positions);
            return new GenericResponse<>("Deleted positions");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete positions"));
        }
    }

    public GenericResponse<String> deleteAllPositions() {
        try {
            repository.deleteAll();
            return new GenericResponse<>("Deleted all positions");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete all positions"));
        }
    }

}
