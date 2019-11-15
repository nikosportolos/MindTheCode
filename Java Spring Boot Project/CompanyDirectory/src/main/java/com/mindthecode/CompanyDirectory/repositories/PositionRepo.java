package com.mindthecode.CompanyDirectory.repositories;

import com.mindthecode.CompanyDirectory.models.entities.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepo extends CrudRepository<Position, Long> {

    @Override
    @RestResource(exported = false)
    void delete(Position entity);

    @Override
    @RestResource(exported = false)
    Position save(Position entity);

}
