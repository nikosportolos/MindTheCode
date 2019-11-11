package com.nikosportolos.MtCProject1.repos;

import com.nikosportolos.MtCProject1.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CompanyRepo extends CrudRepository<Company, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Company> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Company> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(Company entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Company> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
