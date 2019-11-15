package com.mindthecode.CompanyDirectory.services;

import com.mindthecode.CompanyDirectory.models.entities.BusinessUnit;
import com.mindthecode.CompanyDirectory.mappers.BusinessUnitMapper;
import com.mindthecode.CompanyDirectory.models.responses.ErrorResponse;
import com.mindthecode.CompanyDirectory.models.responses.GenericResponse;
import com.mindthecode.CompanyDirectory.repositories.BusinessUnitRepository;
import com.mindthecode.CompanyDirectory.models.responses.AllBusinessUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            if (businessUnit.getId() == id)
                return new GenericResponse(mapper.mapBusinessUnitToResponse(businessUnit));
        }
        return new GenericResponse(new ErrorResponse(0, "Error", "BusinessUnit with id " + id + " does not exist"));
    }
}
