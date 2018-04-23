package com.elipcero.classroomschool.services;

import com.elipcero.classroomschool.domains.ClassCustomer;
import com.elipcero.classroomschool.repositories.ClassCalendarRepository;
import com.elipcero.classroomschool.web.CustomerClass;
import com.elipcero.schoolcore.eventsourcing.EventMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerClassService {

    @NonNull private final CustomerClassResource resource;
    @NonNull private final ClassCalendarRepository calendarRepository;

    public boolean registers(CustomerClass customerClass) {
        return calendarRepository.findById(customerClass.getClassId()).map( c -> {
            resource.registers(c.getClassCalendarDay().stream().map(d ->
                new EventMessage<>("CustomerRegistered",
                        ClassCustomer.builder()
                                .classCalendarId(c.getId())
                                .classCalendarName(c.getName())
                                .clientId(customerClass.getCustomerId())
                                .clientName(customerClass.getCustomerName())
                                .classCalendarDayId(d.getDayOfWeek().getValue())
                                .build()
                )).collect(Collectors.toList()));
            return true;
        }).orElse(false);
    }

    public boolean unRegister(int customerId, int classId) {
        return calendarRepository.findById(classId).map( c -> {
            resource.registers(c.getClassCalendarDay().stream().map(d ->
                    new EventMessage<>("CustomerUnregistered",
                            ClassCustomer.builder()
                                    .classCalendarId(c.getId())
                                    .classCalendarName(c.getName())
                                    .clientId(customerId)
                                    .clientName("")
                                    .classCalendarDayId(d.getDayOfWeek().getValue())
                                    .build()
                    )).collect(Collectors.toList()));
            return true;
        }).orElse(false);
    }
}
