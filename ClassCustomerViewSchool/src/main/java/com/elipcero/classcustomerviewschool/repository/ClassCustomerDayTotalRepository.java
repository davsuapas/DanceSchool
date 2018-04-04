package com.elipcero.classcustomerviewschool.repository;

import com.elipcero.classcustomerviewschool.domain.ClassCustomerDayTotal;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ClassCustomerDayTotalRepository extends Repository<ClassCustomerDayTotal, Integer> {

    @RestResource
    Optional<ClassCustomerDayTotal> findById(Integer var1);
}
