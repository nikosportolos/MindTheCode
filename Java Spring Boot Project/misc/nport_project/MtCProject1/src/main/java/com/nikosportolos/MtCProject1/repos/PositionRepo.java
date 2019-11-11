package com.nikosportolos.MtCProject1.repos;

import com.nikosportolos.MtCProject1.models.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface PositionRepo extends CrudRepository<Position, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Position> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Position> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(Position entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Position> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
