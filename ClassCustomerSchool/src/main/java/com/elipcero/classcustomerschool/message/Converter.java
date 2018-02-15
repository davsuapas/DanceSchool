package com.elipcero.classcustomerschool.message;

import com.elipcero.classcustomerschool.domain.ClassCustomer;
import com.elipcero.classcustomerschool.domain.ClassCustomerEvent;

public class Converter {

    public static ClassCustomerEvent convertToClassCustomerEvent(long eventId, ClassCustomerMessage message) {
        return new ClassCustomerEvent(
                eventId,
                message.getEventType(),
                ClassCustomer.builder()
                        .clientId(message.getClientId())
                        .classCalendarId(message.getClassCalendarId())
                        .classCalendarDayId(message.getClassCalendarDayId()).build()
        );
    }

    public static ClassCustomerMessage convertToClassCustomerMessage(ClassCustomerEvent event) {
        ClassCustomer entity = event.getEntity();
        return ClassCustomerMessage.builder()
                .classCalendarDayId(entity.getClassCalendarDayId())
                .classCalendarId(entity.getClassCalendarId())
                .clientId(entity.getClientId())
                .eventType(event.getEventType()).build();
    }
}
