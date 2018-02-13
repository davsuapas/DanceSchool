package com.elipcero.classcustomerschool.web;

import com.elipcero.classcustomerschool.domain.ClassCustomer;
import com.elipcero.classcustomerschool.domain.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClassCustomerEvent {

    private String eventType;
    private int clientId;
    private int classCalendarId;
    private int classCalendarDayId;

    public Event<ClassCustomer> convertToEvent(long eventId) {
        return new Event<>(
                eventId,
                this.eventType,
                ClassCustomer.builder()
                    .clientId(this.clientId)
                    .classCalendarId(this.classCalendarId)
                    .classCalendarDayId(this.classCalendarDayId).build()
        );
    }
}
