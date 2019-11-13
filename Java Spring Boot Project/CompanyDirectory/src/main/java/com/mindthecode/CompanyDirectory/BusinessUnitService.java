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

    public GenericResponse<List<BusinessUnitResponse>> getBusinessUnits(){
        Iterable<BusinessUnit> retrievedBusinessUnits = repository.findAll();
        List<BusinessUnitResponse> businessUnits = new ArrayList<>();
        for(BusinessUnit businessUnit : retrievedBusinessUnits){
            businessUnits.add(mapper.mapBusinessinessUnitResponseFromBusinessUnit(businessUnit));
        }
        return new GenericResponse<>(businessUnits);
    }
}
