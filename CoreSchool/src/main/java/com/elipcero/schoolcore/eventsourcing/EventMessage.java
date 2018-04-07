package com.elipcero.schoolcore.eventsourcing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventMessage<T> {

    public EventMessage(String eventType, T entity) {
        this.eventType = eventType;
        this.entity = entity;
    }

    private long id;
    private String eventType;
    private T entity;
}
