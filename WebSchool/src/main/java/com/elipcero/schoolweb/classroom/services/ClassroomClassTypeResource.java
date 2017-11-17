package com.elipcero.schoolweb.classroom.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.elipcero.schoolweb.classroom.domains.ClassroomClassType;

@FeignClient(name = "classroom-school")
public interface ClassroomClassTypeResource {

    @GetMapping(value = "classroomClassTypes/{id}?projection=all")
    Resource<ClassroomClassType> getClassroomClassTypeById(@PathVariable("id") String id);
    
    @DeleteMapping(value = "classroomClassTypes/{id}")
    void delete(@PathVariable("id") String id);
    
    @PostMapping(value = "classroomClassTypes", consumes = MediaType.APPLICATION_JSON_VALUE)
    Resource<ClassroomClassType> add(@RequestBody String classroomClassType);

    @PutMapping(value = "classroomClassTypes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	Resource<ClassroomClassType> update(@PathVariable("id") String id, @RequestBody String classroomClassType);

    @GetMapping(value = "classroomClassTypes")
	Resources<ClassroomClassType> getAll();
}
