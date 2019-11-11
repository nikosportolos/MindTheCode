package com.nikosportolos.MtCProject1.repos;

import com.nikosportolos.MtCProject1.models.BusinessUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface BusinessUnitRepo extends CrudRepository<BusinessUnit, Long> {

    @Override
    @RestResource(exported = false)
    <S extends BusinessUnit> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends BusinessUnit> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(BusinessUnit entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends BusinessUnit> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
