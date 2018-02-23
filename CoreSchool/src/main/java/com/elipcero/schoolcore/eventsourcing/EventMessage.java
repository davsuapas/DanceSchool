package com.elipcero.schoolcore.eventsourcing;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventMessage<T> {
    private String eventType;
    private T entity;
}
