package com.elipcero.schoolcore.eventsourcing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventMessage<T> {
    private long id;
    private String eventType;
    private T entity;
}
