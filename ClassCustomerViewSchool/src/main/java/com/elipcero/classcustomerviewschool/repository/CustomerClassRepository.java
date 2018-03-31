package com.elipcero.classcustomerviewschool.repository;

import com.elipcero.classcustomerviewschool.domain.CustomerClass;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CustomerClassRepository extends Repository<CustomerClass, Integer> {

    @RestResource
    Optional<CustomerClass> findById(Integer var1);
}
