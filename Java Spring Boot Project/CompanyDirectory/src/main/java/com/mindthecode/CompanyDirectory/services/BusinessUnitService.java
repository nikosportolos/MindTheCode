package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.mappers.BusinessUnitMapper;
import com.mindthecode.CompanyDirectory.models.responses.BusinessUnitResponse;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.BusinessUnitRepository;
import com.mindthecode.CompanyDirectory.models.responses.AllBusinessUnitResponse;
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
        return new GenericResponse<>(new AllBusinessUnitResponse(mapper.mapBusinessUnits(repository.findAll())));
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
        return new GenericResponse<>(new ErrorResponse(0, "Error", "BusinessUnit with id " + id + " does not exist"));
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
}
