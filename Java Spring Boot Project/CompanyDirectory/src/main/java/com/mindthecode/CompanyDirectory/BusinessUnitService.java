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
        /*Iterable<BusinessUnit> retrievedBusinessUnits = repository.findAll();
        List<BusinessUnitResponse> businessUnits = new ArrayList<>();
        for(BusinessUnit businessUnit : retrievedBusinessUnits){
            businessUnits.add(mapper.mapBusinessinessUnitToResponse(businessUnit));
        }*/
        return new GenericResponse<>(new AllBusinessUnitResponse(mapper.mapBusinessUnits(repository.findAll())));
    }
}
