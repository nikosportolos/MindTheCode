package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.UnitMapper;
import com.mindthecode.CompanyDirectory.models.responses.AllUnitsResponse;
import com.mindthecode.CompanyDirectory.models.entities.Unit;
import com.mindthecode.CompanyDirectory.models.responses.UnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UnitService {

    @Autowired
    private UnitRepository repository;

    @Autowired
    private UnitMapper mapper;

    public GenericResponse<AllUnitsResponse> getAllUnits() {
        List<UnitResponse> units = mapper.mapUnits(repository.findAll());
        if (units == null || units.size() > 0)
            return new GenericResponse<>(new AllUnitsResponse(units));

        return new GenericResponse<>(new ErrorResponse(0, "Error", "No units were found"));
    }

    public GenericResponse<AllUnitsResponse> getUnitById(long id) {
        List<UnitResponse> unitResponses = new ArrayList<>();
        Iterable<Unit> retrievedUnits = repository.findAll();

        for (Unit unit : retrievedUnits) {
            if (unit.getId() == id)
                unitResponses.add(mapper.mapUnitToResponse(unit));
        }

        if (unitResponses.size() == 0)
            return new GenericResponse<>(new ErrorResponse(0, "Unknown unit", "No unit found with id " + id));

        return new GenericResponse<>(new AllUnitsResponse(unitResponses));
    }

    public GenericResponse<String> saveUnit(Unit unit) {
        try {
            repository.save(unit);
            return new GenericResponse<>("Saved unit #" + unit.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save unit"));
        }
    }

    public GenericResponse<String> saveUnits(Iterable<Unit> units) {
        try {
            repository.saveAll(units);
            return new GenericResponse<>("Saved units");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save unit"));
        }
    }

    public GenericResponse<String> deleteUnit(Unit unit) {
        try {
            repository.delete(unit);
            return new GenericResponse<>("Deleted unit #" + unit.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete unit"));
        }
    }

    public GenericResponse<String> deleteUnits(Iterable<Unit> units) {
        try {
            repository.deleteAll(units);
            return new GenericResponse<>("Deleted units");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete units"));
        }
    }

    public GenericResponse<String> deleteAllUnits() {
        try {
            repository.deleteAll();
            return new GenericResponse<>("Deleted all units");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete all units"));
        }
    }


}
