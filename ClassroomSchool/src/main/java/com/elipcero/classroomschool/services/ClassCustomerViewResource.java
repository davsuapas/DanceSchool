package com.elipcero.classroomschool.services;

import com.elipcero.classroomschool.domains.ClassCustomerDayTotal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeoutException;

@FeignClient(name = "classcustomerviewschool", fallback = ClassCustomerViewResourceFallback.class)
public interface ClassCustomerViewResource {

    @GetMapping(value = "classCustomerDayTotals/{id}")
    Resource<ClassCustomerDayTotal> getCalendarSummaryByClassId(@PathVariable("id") int id) throws TimeoutException;

    @GetMapping(value = "customerClasses/search/existsByCustomerIdAndClassId?customerId={customerId}&classId={classId}")
    boolean existsByCustomerIdAndClassId(@PathVariable("customerId") int customerId, @PathVariable("classId") int classId);
}

