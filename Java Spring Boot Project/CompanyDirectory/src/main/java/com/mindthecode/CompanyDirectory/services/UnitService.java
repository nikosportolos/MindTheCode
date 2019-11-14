package com.mindthecode.CompanyDirectory.services;


import com.mindthecode.CompanyDirectory.mappers.UnitMapper;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.AllUnitsResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

    @Autowired
    private UnitRepo repo;

    @Autowired
    private UnitMapper mapper;

    public GenericResponse<AllUnitsResponse> getAllUnits() {
        return new GenericResponse<>(new AllUnitsResponse(mapper.mapUnits(repo.findAll())));
    }

    public GenericResponse<AllUnitsResponse> getUnitById(long id) {
        // Check if unit exists in database
        boolean unitFound = repo.findById(id).isPresent();

        if (unitFound) {
            // Fetch unit with the specified id from database
            Unit retrievedUnit = repo.findById(id).get();
            return new GenericResponse<>(new AllUnitsResponse(mapper.mapUnitToResponse(retrievedUnit)));
        } else {
            return new GenericResponse<>(new ErrorResponse(0, "Unknown Unit", "There is no unit with id " + id));
        }
    }
}
