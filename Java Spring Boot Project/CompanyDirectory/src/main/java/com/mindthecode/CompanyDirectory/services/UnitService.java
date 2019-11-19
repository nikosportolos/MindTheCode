package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.mappers.UnitMapper;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.AllUnitsResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.models.responses.UnitResponse;
import com.mindthecode.CompanyDirectory.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    @Autowired
    private UnitRepository repository;

    @Autowired
    private UnitMapper mapper;

    public GenericResponse<AllUnitsResponse> getAllUnits() {
        List<UnitResponse> units = mapper.mapUnits(repository.findAll());
        if (units == null || units.size() == 0)
            return new GenericResponse<>(new ErrorResponse(0, "Error", "No units found"));

        return new GenericResponse<>(new AllUnitsResponse(units));
    }

    public GenericResponse<AllUnitsResponse> getUnitById(long id) {
        // Check if unit exists in database
        boolean unitFound = repository.findById(id).isPresent();

        if (unitFound) {
            // Fetch unit with the specified id from database
            Unit retrievedUnit = repository.findById(id).get();
            return new GenericResponse<>(new AllUnitsResponse(mapper.mapUnitToResponse(retrievedUnit)));
        } else {
            return new GenericResponse<>(new ErrorResponse(0, "Unknown unit", "No unit found with id " + id));
        }
    }

    public GenericResponse<String> saveUnit(Unit unit) {
        try {
            repository.save(unit);
            return new GenericResponse<>("company unit #" + unit.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save unit"));
        }
    }
}
