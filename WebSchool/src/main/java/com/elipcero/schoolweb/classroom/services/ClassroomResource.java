package com.elipcero.schoolweb.classroom.services;

import com.elipcero.schoolweb.classroom.domains.Classroom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "classroomschool")
public interface ClassroomResource {

    @GetMapping(value = "classrooms/{id}")
    Resource<Classroom> getClassroomById(@PathVariable("id") Integer id);
    
    @DeleteMapping(value = "classrooms/{id}")
    void delete(@PathVariable("id") Integer id);
    
    @PostMapping(value = "classrooms")
    Resource<Classroom> add(@RequestBody Classroom classroom);

    @PutMapping(value = "classrooms/{id}")
	Resource<Classroom> update(@PathVariable("id") Integer id, @RequestBody Classroom classroom);

    @GetMapping(value = "classrooms")
	Resources<Classroom> getAll();
}
