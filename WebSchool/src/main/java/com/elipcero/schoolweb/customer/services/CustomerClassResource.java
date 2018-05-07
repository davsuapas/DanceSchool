package com.elipcero.schoolweb.customer.services;

import com.elipcero.schoolweb.customer.domain.CustomerClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "classcustomerviewschool")
public interface CustomerClassResource {

    @GetMapping(value = "customerClasses/{id}")
    Resource<CustomerClass> getClassesByCustomerId(@PathVariable("id") int id);
}
