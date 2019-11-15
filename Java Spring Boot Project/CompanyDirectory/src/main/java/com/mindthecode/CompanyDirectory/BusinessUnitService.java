package com.mindthecode.CompanyDirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessUnitService {

    @Autowired
    private BusinessUnitMapper mapper;

    @Autowired
    private BusinessUnitRepository repository;

    public GenericResponse<AllBusinessUnitResponse> getAllBusinessUnits(){
        return new GenericResponse<>(new AllBusinessUnitResponse(mapper.mapBusinessUnits(repository.findAll())));
    }

    public GenericResponse<AllBusinessUnitResponse> getBusinessUnitById(long id) {
        Iterable<BusinessUnit> retrievedBusinessUnits = repository.findAll();
        for(BusinessUnit businessUnit : retrievedBusinessUnits){
            if(businessUnit.getId() == id)
                return new GenericResponse(mapper.mapBusinessinessUnitToResponse(businessUnit));
        }
        return new GenericResponse(new Error(0,"Error","BusinessUnit with id "+ id +" does not exist"));
    }
}
