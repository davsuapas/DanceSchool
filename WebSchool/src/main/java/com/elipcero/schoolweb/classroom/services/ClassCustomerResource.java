package com.elipcero.schoolweb.classroom.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "classroom-school")
public interface ClassCustomerResource {

    @PostMapping(value = "customerClasses")
    void register(@RequestBody CustomerClass customerClass);

    @DeleteMapping(value = "customerClasses/{customerId}/{classId}")
    void delete(@PathVariable("customerId") int customerId, @PathVariable("classId") int classId);
}
