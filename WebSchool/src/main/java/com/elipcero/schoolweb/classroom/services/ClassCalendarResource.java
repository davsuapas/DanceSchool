package com.elipcero.schoolweb.classroom.services;

import com.elipcero.schoolweb.classroom.domains.ClassCalendar;
import com.elipcero.schoolweb.classroom.domains.ClassCalendarView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "classroomschool")
public interface ClassCalendarResource {

    @GetMapping(value = "classCalendars/{id}")
    Resource<ClassCalendarView> getClassCalendarById(@PathVariable("id") int id);

    @GetMapping(value = "classCalendars/search/numberOfRecords?classroomId={classroomId}&classTypeId={classTypeId}")
    int numberOfRecords(@PathVariable("classroomId") int classroomId, @PathVariable("classTypeId") int classTypeId);

    @DeleteMapping(value = "classCalendars/{id}")
    void delete(@PathVariable("id") Integer id);
    
    @PostMapping(value = "classCalendars")
    Resource<ClassCalendar> post(@RequestBody ClassCalendar classCalendar);

    @GetMapping(value = "classCalendars")
	Resources<ClassCalendarView> getAll();
}
