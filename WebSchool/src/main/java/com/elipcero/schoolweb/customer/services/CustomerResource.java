package com.elipcero.schoolweb.customer.services;

import com.elipcero.schoolweb.customer.domain.Customer;
import com.elipcero.schoolweb.customer.domain.CustomerDescriptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "customerschool")
public interface CustomerResource {
	
    @GetMapping(value = "customers/search/secondName?secondName={secondName}&projection=descriptor")
    Resources<CustomerDescriptor> findBySecondName(@PathVariable("secondName") String secondName);
    
    @GetMapping(value = "customers/search/secondAndThirdName?secondName={secondName}&thirdName={thirdName}&projection=descriptor")
    Resources<CustomerDescriptor> findBySecondAndThirdName(@PathVariable("secondName") String secondName, @PathVariable("thirdName") String thirdName);
    
    @GetMapping(value = "customers/{id}")
    Resource<Customer> getCustomerById(@PathVariable("id") Integer id);
    
    @DeleteMapping(value = "customers/{id}")
    void delete(@PathVariable("id") Integer id);
    
    @PostMapping(value = "customers")
    Resource<Customer> add(@RequestBody Customer customer);

    @PutMapping(value = "customers/{id}")
	Resource<Customer> update(@PathVariable("id") Integer id, @RequestBody Customer customer);
}