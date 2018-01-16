package com.elipcero.schoolweb.classroom.services;

import com.elipcero.schoolweb.classroom.domains.ClassCalendar;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "classroom-school")
public interface ClassCalendarResource {

    @GetMapping(value = "classCalendars/{id}")
    Resource<ClassCalendar> getClassCalendarById(@PathVariable("id") int id);

    @GetMapping(value = "classCalendars/search/numberOfRecords?classroomId={classroomId}&classTypeId={classTypeId}")
    int numberOfRecords(@PathVariable("classroomId") int classroomId, @PathVariable("classTypeId") int classTypeId);

    @DeleteMapping(value = "classCalendars/{id}")
    void delete(@PathVariable("id") Integer id);
    
    @PostMapping(value = "classCalendars")
    Resource<ClassCalendar> post(@RequestBody ClassCalendar classCalendar);

    @GetMapping(value = "classCalendars")
	Resources<ClassCalendar> getAll();
}
