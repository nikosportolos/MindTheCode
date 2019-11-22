package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.mappers.BusinessUnitMapper;
import com.mindthecode.CompanyDirectory.models.responses.*;
import com.mindthecode.CompanyDirectory.repositories.BusinessUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessUnitService {

    @Autowired
    private BusinessUnitMapper mapper;

    @Autowired
    private BusinessUnitRepository repository;

    public GenericResponse<AllBusinessUnitResponse> getAllBusinessUnits() {
        List<BusinessUnitResponse> businessUnits = mapper.mapBusinessUnits(repository.findAll());
        if (businessUnits == null || businessUnits.size() > 0)
            return new GenericResponse<>(new AllBusinessUnitResponse(businessUnits));

        return new GenericResponse<>(new ErrorResponse(0, "Error", "No business units were found"));
    }

    public GenericResponse<AllBusinessUnitResponse> getBusinessUnitById(long id) {
        Iterable<BusinessUnit> retrievedBusinessUnits = repository.findAll();
        for (BusinessUnit businessUnit : retrievedBusinessUnits) {
            if (businessUnit.getId() == id) {
                List<BusinessUnitResponse> list = new ArrayList<>();
                list.add(mapper.mapBusinessUnitToResponse(businessUnit));
                return new GenericResponse<>(new AllBusinessUnitResponse(list));
            }
        }
        return new GenericResponse<>(new ErrorResponse(0, "Unknown business unit", "No business unit found with id " + id));
    }

    public GenericResponse<String> saveBusinessUnit(BusinessUnit businessUnit) {
        try {
            repository.save(businessUnit);
            return new GenericResponse<>("Saved business unit #" + businessUnit.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save business unit"));
        }
    }

    public GenericResponse<String> saveBusinessUnits(Iterable<BusinessUnit> businessUnits) {
        try {
            repository.saveAll(businessUnits);
            return new GenericResponse<>("Saved businessUnits");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not save business units"));
        }
    }

    public GenericResponse<String> deleteBusinessUnit(BusinessUnit businessUnit) {
        try {
            repository.delete(businessUnit);
            return new GenericResponse<>("Deleted business unit #" + businessUnit.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete business unit#" + businessUnit.getId()));
        }
    }

    public GenericResponse<String> deleteBusinessUnits(Iterable<BusinessUnit> businessUnits) {
        try {
            repository.deleteAll(businessUnits);
            return new GenericResponse<>("Deleted business units");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete business units"));
        }
    }

    public GenericResponse<String> deleteAllBusinessUnits() {
        try {
            repository.deleteAll();
            return new GenericResponse<>("Deleted all business units");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GenericResponse<>(new ErrorResponse(0, "Error", "Could not delete all business units"));
        }
    }
}
