package com.elipcero.classroomschool.services;

import com.elipcero.classroomschool.domains.ClassCustomer;
import com.elipcero.schoolcore.eventsourcing.EventMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "classcustomerschool")
public interface CustomerClassResource {

    @PostMapping(value = "classcustomers/registers")
    void registers(List<EventMessage<ClassCustomer>> events);
}
