package com.elipcero.schoolweb.classroom.services;

import com.elipcero.schoolweb.classroom.domains.ClassType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "classroomschool")
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
