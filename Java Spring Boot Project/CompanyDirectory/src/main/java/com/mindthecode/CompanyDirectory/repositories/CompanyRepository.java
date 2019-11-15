package com.mindthecode.CompanyDirectory.repositories;
import com.mindthecode.CompanyDirectory.models.entities.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long>
{
    @Override
    @RestResource(exported = false)
    void deleteAll();
}
