package com.mindthecode.CompanyDirectory.repositories;

import com.mindthecode.CompanyDirectory.models.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long>
{
    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);


    @Override
    @RestResource(exported = false)
    void delete(Task entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
