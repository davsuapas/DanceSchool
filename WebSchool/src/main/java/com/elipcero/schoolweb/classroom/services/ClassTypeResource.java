package com.elipcero.schoolweb.classroom.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.elipcero.schoolweb.classroom.domains.ClassType;

@FeignClient(name = "classroom-school")
public interface ClassTypeResource {

    @GetMapping(value = "classTypes/{id}")
    Resource<ClassType> getClassTypeById(@PathVariable("id") Integer id);
    
    @DeleteMapping(value = "classTypes/{id}")
    void delete(@PathVariable("id") Integer id);
    
    @PostMapping(value = "classTypes")
    Resource<ClassType> add(@RequestBody ClassType classType);

    @PutMapping(value = "classTypes/{id}")
	Resource<ClassType> update(@PathVariable("id") Integer id, @RequestBody ClassType classType);

    @GetMapping(value = "classTypes")
	Resources<ClassType> getAll();
}
