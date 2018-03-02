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
                        .classCalendarId(classCustomer.getClassCalendarId())
                        .classCalendarDayId(classCustomer.getClassCalendarDayId()).build()
        );
    }

    public static EventMessage<ClassCustomer> convertToClassCustomerMessage(ClassCustomerEvent event) {
        ClassCustomer entity = event.getEntity();
        return new EventMessage(
                event.getEventType(),
                ClassCustomer.builder()
                    .classCalendarDayId(entity.getClassCalendarDayId())
                    .classCalendarId(entity.getClassCalendarId())
                    .clientId(entity.getClientId()).build());
    }
}
