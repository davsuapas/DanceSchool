package com.elipcero.classcustomerschool.message;

import com.elipcero.classcustomerschool.domain.ClassCustomer;
import com.elipcero.classcustomerschool.domain.ClassCustomerEvent;
import com.elipcero.schoolcore.eventsourcing.EventMessage;

public class Converter {

    public static ClassCustomerEvent convertToClassCustomerEvent(long eventId, EventMessage<ClassCustomer> message) {
        ClassCustomer classCustomer = message.getEntity();
        return new ClassCustomerEvent(
                eventId,
                message.getEventType(),
                ClassCustomer.builder()
                        .clientId(classCustomer.getClientId())
                        .clientName(classCustomer.getClientName())
                        .classCalendarId(classCustomer.getClassCalendarId())
                        .classCalendarName(classCustomer.getClassCalendarName())
                        .classCalendarDayId(classCustomer.getClassCalendarDayId()).build()
        );
    }

    public static EventMessage<ClassCustomer> convertToClassCustomerMessage(ClassCustomerEvent event) {
        ClassCustomer entity = event.getEntity();
        return new EventMessage(
                event.getId(),
                event.getEventType(),
                ClassCustomer.builder()
                    .clientId(entity.getClientId())
                    .clientName(entity.getClientName())
                    .classCalendarId(entity.getClassCalendarId())
                    .classCalendarName(entity.getClassCalendarName())
                    .classCalendarDayId(entity.getClassCalendarDayId())
                        .build());
    }
}
