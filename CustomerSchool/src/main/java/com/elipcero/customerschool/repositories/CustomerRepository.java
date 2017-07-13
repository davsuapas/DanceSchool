package com.elipcero.customerschool.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.elipcero.customerschool.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	@RestResource(path = "secondName", rel = "secondName")
	List<Customer> findBySecondName(@Param("secondName") String secondName);
	
	@RestResource(path = "secondAndThirdName", rel = "secondAndThridName")
	List<Customer> findBySecondNameAndThirdName(@Param("secondName") String secondName, @Param("thirdName") String thirdName);
}
